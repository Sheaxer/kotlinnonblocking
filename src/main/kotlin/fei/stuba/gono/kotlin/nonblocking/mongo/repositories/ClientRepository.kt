package fei.stuba.gono.kotlin.nonblocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.Client
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

/***
 * Interface extending CrudRepository for Client.
 * @see Client
 * @see CrudRepository
 */
@Repository
interface ClientRepository : ReactiveCrudRepository<Client,String> {
}