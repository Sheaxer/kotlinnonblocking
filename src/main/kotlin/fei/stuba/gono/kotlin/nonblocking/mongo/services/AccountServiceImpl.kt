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
 * MongoDB implementation of service that manages nonblocking marshalling and de-marshalling
 * Account objects using CRUD operrations and auto generated instance of AccountRepository. Uses Kotlin coroutines.
 *Implementácia služby ktorá spravuje marshalling a de-marhalling objektov triedy Account
 * pomocou CRUD operácii a inštanciu rozhrania AccountRepository. Využíva coroutines jazyka Kotlin.
 * @param accountRepository Repository providing CRUD operations on Account entities.
 * Repozitár poskytujúce CRUD operácie nad entitami triedy Account.
 */
@Service
class AccountServiceImpl @Autowired constructor(private val accountRepository: AccountRepository):
        AccountService {
    /***
     * Retrieves the entity  identified by IBAN - transforms Mono into Account? with kotlin coroutines.
     *
     *  Získa entitu identifikovanú IBAN-om, transformuje Mono stream do inštancie Account? za
     * použitie kotlin coroutines.
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
     *  Získa entitu identifikovanú číslom účtu, transformuje Mono na inštanciu triedy Account? za
     * použitie kotlin coroutines.
     * @see awaitFirstOrNull
     * @param number local account number of desired entity, must not be null.
     * @return value of the entity or null if none found.
     */
    override suspend fun getAccountByLocalAccountNumber(number: String) : Account?
    {
        return accountRepository.findAccountByLocalAccountNumber(number).awaitFirstOrNull()
    }
}