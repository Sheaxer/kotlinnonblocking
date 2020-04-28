package fei.stuba.gono.kotlin.nonblocking.mongo.services

import fei.stuba.gono.kotlin.nonblocking.mongo.repositories.AccountRepository
import fei.stuba.gono.kotlin.nonblocking.services.AccountService
import fei.stuba.gono.kotlin.pojo.Account
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Service
/***
 * MongoDB implementation of service that manages nonblocking marshalling and de-marshalling Account objects.
 * Uses Kotlin coroutines.
 */
@Service
class AccountServiceImpl @Autowired constructor(private val accountRepository: AccountRepository):
        AccountService {
    /***
     * Retrieves the entity  identified by IBAN - transforms Mono into Account? with kotlin coroutines.
     * @see awaitFirstOrNull
     * @param iban IBAN of desired entity, must not be null.
     * @return value of the entity or null if none found.
     */
    override suspend fun getAccountByIban(iban: String) : Account?
    {
       return accountRepository.findAccountByIban(iban).awaitFirstOrNull()

    }

    /***
     * Retrieves the entity  identified by a local account number -
     * transforms Mono into Account? with kotlin coroutines.
     * @see awaitFirstOrNull
     * @param number local account number of desired entity, must not be null.
     * @return value of the entity or null if none found.
     */
    override suspend fun getAccountByLocalAccountNumber(number: String) : Account?
    {
        return accountRepository.findAccountByLocalAccountNumber(number).awaitFirstOrNull()
    }
}