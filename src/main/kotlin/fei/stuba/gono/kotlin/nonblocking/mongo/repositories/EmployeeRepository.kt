package fei.stuba.gono.kotlin.nonblocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*
/***
 * Interface extending CrudRepository for Employee.
 * @see Employee
 * @see CrudRepository
 */
@Repository
interface EmployeeRepository : ReactiveCrudRepository<Employee,String> {
    /***
     * Finds the entity with the given user name.
     * @param username User
     * @return Optional containing the entity or Optional.empty() if no entity is found.
     */
    fun findEmployeeByUsername(username: String) : Mono<Employee?>
}