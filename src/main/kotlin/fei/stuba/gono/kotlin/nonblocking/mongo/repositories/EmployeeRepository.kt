package fei.stuba.gono.kotlin.nonblocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*
/***
 * <div class="en">Interface extending ReactiveCrudRepository for Employee entities. Automatically instantiated
 * by Spring.</div>
 * <div class="sk">Rozhranie rozširujúce ReactiveCrudRepository pre entity triedy Employee. Automaticky inštanciované
 * pomocou Spring.</div>
 * @see Employee
 * @see org.springframework.data.repository.reactive.ReactiveCrudRepository
 */
@Repository
interface EmployeeRepository : ReactiveCrudRepository<Employee,String> {
    /***
     * <div class="en">Finds the entity with the given user name.</div>
     * <div class="sk">Nájde entitu so zadaným používateľským menom.</div>
     * @param userName <div class="en">user name of the required entity, must not be null.</div>
     *                 <div class="sk">používateľské meno žiadanej entity, nesmie byť null.</div>
     * @return <div class="en">Mono emitting the entity or Mono.empty() if no entity was found.</div>
     * <div class="sk">Mono emitujúce žiadanú entitu alebo Mono.empty() ak nebolo možné entitu nájsť.</div>
     */
    fun findEmployeeByUsername(username: String) : Mono<Employee?>

    /***
     * <div class="en">Checks if the entity with the given user name exists.</div>
     * <div class="sk">Skontroluje, či entita so zadaným užívateľským menom existuje.</div>
     * @param username <div class="en">user name of the entity, must not be null.</div>
     * <div class="en"> používateľské meno entity, nesmie byť null.</div>
     * @return <div class="en">Mono emitting true if entity exists, false otherwise.</div>
     * <div class="sk">Mono emitujúce true ak entita existuje, inak false.</div>
     */
    fun existsByUsername(username: String): Mono<Boolean>
}