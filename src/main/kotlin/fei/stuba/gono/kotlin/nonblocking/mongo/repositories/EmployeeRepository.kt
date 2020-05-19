package fei.stuba.gono.kotlin.nonblocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*
/***
 * Interface extending ReactiveCrudRepository for Employee entities. Automatically instantiated
 * by Spring.
 * Rozhranie rozširujúce ReactiveCrudRepository pre entity triedy Employee. Automaticky inštanciované
 * pomocou Spring.
 * @see Employee
 * @see org.springframework.data.repository.reactive.ReactiveCrudRepository
 */
@Repository
interface EmployeeRepository : ReactiveCrudRepository<Employee,String> {
    /***
     * Finds the entity with the given user name.
     * Nájde entitu so zadaným používateľským menom.
     * @param username user name of the required entity, must not be null.
     *                 používateľské meno žiadanej entity, nesmie byť null.
     * @return Mono emitting the entity or Mono.empty() if no entity was found.
     * Mono emitujúce žiadanú entitu alebo Mono.empty() ak nebolo možné entitu nájsť.
     */
    fun findEmployeeByUsername(username: String) : Mono<Employee?>

    /***
     * Checks if the entity with the given user name exists.
     * Skontroluje, či entita so zadaným užívateľským menom existuje.
     * @param username user name of the entity, must not be null.
     *  používateľské meno entity, nesmie byť null.
     * @return Mono emitting true if entity exists, false otherwise.
     * Mono emitujúce true ak entita existuje, inak false.
     */
    fun existsByUsername(username: String): Mono<Boolean>
}