package fei.stuba.gono.kotlin.nonblocking.services

import fei.stuba.gono.kotlin.nonblocking.pojo.ReportedOverlimitTransaction
import fei.stuba.gono.kotlin.nonblocking.errors.ReportedOverlimitTransactionValidationException
import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionBadRequestException
import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionNotFoundException

/***
 * Interface for marshalling and de-marshalling ReportedOverlimitTransaction  entities.
 */
interface ReportedOverlimitTransactionService {

    /***
     * Generates a new id and saves the entity.
     * @param transaction entity to be saved.
     * @return Mono emitting the saved entity or Mono.error(ReportedOverlimitTransactionValidationException) if
     * the entity is not valid.
     * @see ReportedOverlimitTransactionValidationException
     */
    @Throws(ReportedOverlimitTransactionValidationException::class)
    suspend fun postTransaction(transaction: ReportedOverlimitTransaction): ReportedOverlimitTransaction

    /***
     * Return entity with the given id.
     * @param id must not be null.
     * @return Mono emitting the entity or Mono.empty() if there is none.
     * @see ReportedOverlimitTransactionValidationException
     */
    suspend fun getTransactionById(id: String): ReportedOverlimitTransaction?

    /***
     * Saves the entity with the given id.
     * @param id id identifying the saved entity.
     * @param transaction entity to be saved
     * @return Mono emitting the saved entity or Mono.error(ReportedOverlimitTransactionValidationException) if
     * the entity is not valid.
     * @see ReportedOverlimitTransactionValidationException
     */
    @Throws(ReportedOverlimitTransactionValidationException::class)
    suspend fun putTransaction(id: String, transaction: ReportedOverlimitTransaction): ReportedOverlimitTransaction
    /***
     * Deletes the entity identified by the given id.
     * @param id id of entity to be deleted.
     * @return Mono emitting when the operation was completed,  Mono.error(ReportedOverlimitTransactionNotFoundException)
     * if the entity with given id was not found or Mono.error(ReportedOverlimitTransactionBadRequestException) if the
     * entity couldn't be deleted because its state is CLOSED.
     * @see  ReportedOverlimitTransactionNotFoundException
     * @see ReportedOverlimitTransactionBadRequestException
     */
    @Throws(ReportedOverlimitTransactionNotFoundException::class,
            ReportedOverlimitTransactionBadRequestException::class)
    suspend fun deleteTransaction(id: String)


}