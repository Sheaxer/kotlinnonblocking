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

@Service
class AccountServiceImpl @Autowired constructor(private val accountRepository: AccountRepository):
        AccountService {

    override suspend fun getAccountByIban(iban: String) : Account?
    {
       return accountRepository.findAccountByIban(iban).awaitFirstOrNull()

    }


    override suspend fun getAccountByLocalAccountNumber(number: String) : Account?
    {
        return accountRepository.findAccountByLocalAccountNumber(number).awaitFirstOrNull()
    }
}