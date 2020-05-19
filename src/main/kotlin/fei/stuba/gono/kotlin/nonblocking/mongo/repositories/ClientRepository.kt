package fei.stuba.gono.kotlin.nonblocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.Client
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

/***
 * Interface extending ReactiveCrudRepository for Client entities. Automatically
 * instantiated by the Spring
 * Rozhranie rozširujúce ReactiveCrudRepository pre entity triedy Client. Automaticky inštanciované
 * pomocou Spring.
 * @see Client
 * @see org.springframework.data.repository.reactive.ReactiveCrudRepository
 */
@Repository
interface ClientRepository : ReactiveCrudRepository<Client,String> {
}