package fei.stuba.gono.kotlin.nonblocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.Client
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

/***
 * <div class="en">Interface extending ReactiveCrudRepository for Client entities. Automatically
 * instantiated by the Spring</div>
 * <div class="sk">Rozhranie rozširujúce ReactiveCrudRepository pre entity triedy Client. Automaticky inštanciované
 * pomocou Spring.</div>
 * @see Client
 * @see org.springframework.data.repository.reactive.ReactiveCrudRepository
 */
@Repository
interface ClientRepository : ReactiveCrudRepository<Client,String> {
}