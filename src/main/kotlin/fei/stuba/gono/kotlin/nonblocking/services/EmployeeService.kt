package fei.stuba.gono.kotlin.nonblocking.services

import com.sun.org.apache.xpath.internal.operations.Bool
import fei.stuba.gono.kotlin.pojo.Employee
import reactor.core.publisher.Mono

/***
 * Interface for marshalling and de-marshalling Employee entities.
 */
interface EmployeeService {
    /***
     * Retrieves the entity identified by the given User Name.
     * @param userName - User Name identifying the entity.
     * @return Mono emitting the entity or Mono.empty() if no entity was found.
     */
    suspend fun findEmployeeByUsername(userName : String): Employee?

    /***
     * Saves the entity.
     * @param employee entity to be saved.
     * @return Mono emitting the saved entity.
     */
    suspend fun saveEmployee(employee: Employee): Employee?

    /***
     * Checks if the entity identified by the given id was found.
     * @param id - must not be null.
     * @return Mono emitting true if the entity was found, false otherwise.
     */
    suspend fun employeeExistsById(id: String): Boolean

    suspend fun validate(employee: Employee)

    fun employeeExistsByUsername(userName: String): Mono<Boolean>
}