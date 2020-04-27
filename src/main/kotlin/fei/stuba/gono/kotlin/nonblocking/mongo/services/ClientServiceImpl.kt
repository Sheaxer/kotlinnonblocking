package fei.stuba.gono.kotlin.nonblocking.mongo.services

import fei.stuba.gono.kotlin.nonblocking.mongo.repositories.ClientRepository
import fei.stuba.gono.kotlin.nonblocking.services.ClientService
import fei.stuba.gono.kotlin.pojo.Client
import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
/***
 *MongoDB implementation of service that handles marshalling and de-marshalling Client objects.
 */
@Service
class ClientServiceImpl @Autowired constructor(private val clientRepository: ClientRepository) :ClientService{
    override suspend fun getClientById(id: String): Client? {
        return clientRepository.findById(id).awaitFirstOrNull()
    }

    override suspend fun clientExistsById(id: String): Boolean {
        return clientRepository.existsById(id).awaitFirstOrElse { false }
    }
}