package fei.stuba.gono.kotlin.nonblocking.mongo.services

import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionBadRequestException
import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionNotFoundException
import fei.stuba.gono.kotlin.nonblocking.errors.ReportedOverlimitTransactionValidationException
import fei.stuba.gono.kotlin.nonblocking.mongo.repositories.ReportedOverlimitTransactionRepository
import fei.stuba.gono.kotlin.nonblocking.pojo.ReportedOverlimitTransaction
import fei.stuba.gono.kotlin.nonblocking.services.*
import fei.stuba.gono.kotlin.nonblocking.validation.ReportedOverlimitTransactionValidator
import fei.stuba.gono.kotlin.pojo.Account
import fei.stuba.gono.kotlin.pojo.State
import javafx.application.Application.launch
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.validation.BeanPropertyBindingResult
import reactor.core.publisher.Mono
import java.time.OffsetDateTime

/***
 * Implementation of ReportedOverlimitTransactionService for use with MongoDB.
 */
@Service
class ReportedOverlimitTransactionServiceImpl @Autowired constructor(
        private val repository: ReportedOverlimitTransactionRepository,
        private val nextSequenceService: NextSequenceService,
        private val validator: ReportedOverlimitTransactionValidator,
        private val accountService: AccountService,
        private val clientService: ClientService,
        private val employeeService: EmployeeService,
        private val organisationUnitService: OrganisationUnitService):
        ReportedOverlimitTransactionService {

    /***
     * Name of the sequence containing maximal value of id that was used to save entity in the repository.
     */
    @Value("\${reportedOverlimitTransaction.transaction.sequenceName:customSequences}")
    private val sequenceName: String = "customSequences"

    /***
     * Generates new id using NextSequenceService and saved entity with this id in the repository. Sets the
     * modification date of entity to the time of saving and sets the state to CREATED.
     * @see NextSequenceService
     * @see State
     * @param transaction entity to be saved.
     * @return saved entity.
     */
    @Throws(ReportedOverlimitTransactionValidationException::class)
    override suspend fun postTransaction(transaction: ReportedOverlimitTransaction): ReportedOverlimitTransaction {
        validation(transaction)
       transaction.modificationDate = OffsetDateTime.now()
        transaction.id = nextSequenceService.getNewId(repository, sequenceName)
        transaction.state=State.CREATED
        return repository.save(transaction).awaitFirst()
    }

    /***
     * Finds entity with the given id in the repository.
     * @param id id of entity.
     * @return Optional containing the entity or Optional.empty() if none found.
     */
    override suspend fun getTransactionById(id: String): ReportedOverlimitTransaction? {
        return repository.findById(id).awaitFirstOrNull()
    }

    /***
     * Saves the entity using the given id in the repository. Sets modification date to time of saving, and if
     * there was no entity with the given id before sets the state to CREATED. Notifies the NextSequenceService to
     * check if the id given is a new maximal value.
     * @param id id which will identify the saved entity.
     * @param transaction entity to be saved.
     * @return saved entity.
     */
    @Throws(ReportedOverlimitTransactionValidationException::class)
    override suspend fun putTransaction(id: String, transaction: ReportedOverlimitTransaction): ReportedOverlimitTransaction {
        validation(transaction)
        transaction.modificationDate = OffsetDateTime.now()
        if(!repository.existsById(id).awaitFirstOrElse { false })
            transaction.state = State.CREATED
        transaction.id = id
        if(transaction.state == null)
            transaction.state=State.CREATED
        nextSequenceService.needsUpdate(sequenceName,id)
        return repository.save(transaction).awaitFirst()
    }

    /***
     * Deletes the entity with the given id if it exists in the repository and its state is not CLOSED.
     * @param id id of entity.
     * @return true if entity with given id was found, its state was not CLOSED and it was deleted.
     * @throws ReportedOverlimitTransactionBadRequestException in case the entity couldn't be deleted because its
     * state was CLOSED.
     */
    @Throws(ReportedOverlimitTransactionBadRequestException::class,
            ReportedOverlimitTransactionNotFoundException::class)
    override suspend fun deleteTransaction(id: String) {
        val trans = repository.findById(id).awaitFirstOrNull() ?:
        throw ReportedOverlimitTransactionNotFoundException("ID_NOT_FOUND")
        if(trans.state == State.CLOSED)
        {
            throw ReportedOverlimitTransactionBadRequestException("STATE_CLOSED")
        }
        else
            repository.deleteById(id).awaitFirst()
    }

    /***
     * Validates that ReportedOverlimitTransaction contains all mandatory properties and that
     * they are valid.
     * @throws ReportedOverlimitTransactionValidationException if validation fails with all error codes.
     */
    @Throws(ReportedOverlimitTransactionValidationException::class)
    override suspend fun validation(transaction: ReportedOverlimitTransaction) {
        coroutineScope {
            val errorsAsync = async {
                println(Thread.currentThread().name)
                val errors = BeanPropertyBindingResult(transaction, ReportedOverlimitTransaction::class.java.name)
                validator.validate(transaction, errors)
                return@async errors
            }

            val clientExist = async {
                println(Thread.currentThread().name)
                if(transaction.clientId != null)
                    return@async clientService.clientExistsById((transaction.clientId!!))
                else return@async true
            }

            val organisationExists = async {
                println(Thread.currentThread().name)
                if(transaction.organisationUnitID != null)
                    return@async organisationUnitService.organisationUnitExistsById(transaction.organisationUnitID!!)
                else return@async true
            }

            val employeeExist = async {
                println(Thread.currentThread().name)
                if(transaction.createdBy != null)
                    return@async employeeService.employeeExistsById(transaction.createdBy!!)
                else return@async true
            }

            val accountExist = async {
                println(Thread.currentThread().name)
                if (transaction.sourceAccount != null) {
                    val acc: Account? = when {
                        transaction.sourceAccount!!.iban != null ->
                            accountService.getAccountByIban(transaction.sourceAccount!!.iban!!)
                        transaction.sourceAccount!!.localAccountNumber != null ->
                            accountService.getAccountByLocalAccountNumber(
                                    transaction.sourceAccount!!.localAccountNumber!!)
                        else -> null
                    }
                    return@async !(acc == null || !acc.isActive)
                }
                else
                    return@async true
            }


            val customErrors = mutableListOf<String>()

            // maps all error codes to String and adds them to gathered error messages.
           errorsAsync.await().allErrors.stream().map { oe -> oe.codes?.get(oe.codes!!.lastIndex) }
                    .forEach { x ->
                        if (x != null) {
                            customErrors.add(x)
                        }
                    }
            if(!clientExist.await())
                customErrors.add("CLIENTID_INVALID")
            if(!organisationExists.await())
                customErrors.add("ORGANISATIONUNIT_INVALID")
            if(!employeeExist.await())
                customErrors.add("CREATEDBY_INVALID")
            if(!accountExist.await())
                customErrors.add("ACCOUNT_OFFLINE")
            // checking if client exists in repository.
            /*if (transaction.clientId != null) {
                if (!clientService.clientExistsById(transaction.clientId!!))
                    customErrors.add("CLIENTID_INVALID")
            }
            // checking if organisationUnit exists in repository
            if (transaction.organisationUnitID != null) {
                if (!organisationUnitService.organisationUnitExistsById(transaction.organisationUnitID!!))
                    customErrors.add("ORGANISATIONUNIT_INVALID")
            }
            // checking if employee exists in repository
            if (transaction.createdBy != null) {
                if (!employeeService.employeeExistsById(transaction.createdBy!!))
                    customErrors.add("CREATEDBY_INVALID")
            }
            // checking if account exists in the repository
            if (transaction.sourceAccount != null) {
                val acc: Account? = when {
                    transaction.sourceAccount!!.iban != null -> accountService.getAccountByIban(transaction.sourceAccount!!.iban!!)
                    transaction.sourceAccount!!.localAccountNumber != null -> accountService.getAccountByLocalAccountNumber(transaction.sourceAccount!!.localAccountNumber!!)
                    else -> Account()
                }
                if (acc == null || !acc.isActive)
                    customErrors.add("ACCOUNT_OFFLINE")

            }*/
            // validaion failed - throw exception with all error codes
            if (customErrors.isNotEmpty())
                throw ReportedOverlimitTransactionValidationException(customErrors)
        }
    }


}