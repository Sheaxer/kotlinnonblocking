package fei.stuba.gono.kotlin.nonblocking.services

import com.sun.org.apache.xpath.internal.operations.Bool
import fei.stuba.gono.kotlin.nonblocking.errors.ReportedOverlimitTransactionValidationException
import fei.stuba.gono.kotlin.pojo.Employee
import reactor.core.publisher.Mono

/***
 * Interface for marshalling and de-marshalling Employee entities.
 * Rozhranie pre marshalling a de-marshalling entít triedy Employee.
 */
interface EmployeeService {
    /***
     * Finds the entity identified by the given user name.
     * Nájde entitu idenfitikovanú zadaným používateľskym menom.
     * @param userName user name identifying the entity.
     *                 používateľské meno identifikujúce entitu.
     * @return Mono emitting the entity or Mono.empty() if no entity was found.
     * entita null ak entita nebola nájdená.
     */
    suspend fun findEmployeeByUsername(userName : String): Employee?

    /***
     * Validates the entity and if it is valid, saves it.
     * Validuje entitu a ak je korektná, uloží ju.
     * @param employee entity to be saved.
     * @return saved entity if the validation was successful.
     * uložená entita ak validácia bola úspešná.
     * @throws ReportedOverlimitTransactionValidationException  exception thrown
     * if validation failed. Contains list of failed validation error codes.
     * výnimka ktorá pozostáva z validačných chybových hlášok,
     * ak validácia skončila neúspešne.
     */
    @Throws(ReportedOverlimitTransactionValidationException::class)
    suspend fun saveEmployee(employee: Employee): Employee

    /***
     * Checks if the entity with the given id exists.
     * Skontroluje či entita so zadaným id existuje.
     * @param id id of the entity, must not be null.
     *           id entity, nesmie byť null.
     * @return true if the entity was found, false otherwise.
     * true ak entita bola nájdená, inak false.
     */
    suspend fun employeeExistsById(id: String): Boolean

    /***
     * Validates the entity.
     * Validuje zadanú entitu.
     * @param employee entity to be validated.
     *                 entita, ktorú treba validovať.
     * @throws ReportedOverlimitTransactionValidationException
     * exception thrown if validation failed. Contains failed validation error codes.
     * výnimka, ktorá pozostáva z validačných chybových hlášok,
     * ak validácia skončila neúspešne.
     */
    @Throws(ReportedOverlimitTransactionValidationException::class)
    suspend fun validate(employee: Employee)

    /***
     * Checks if entity with the given user name exists.
     * Skontroluje, či entita so zadaným používateľským menom existuje.
     * @param userName user name of the entity.
     *                 používateľské meno entity.
     * @return Mono emitting true if the entity exists, false otherwise.
     * Mono emitujúce true ak entita existuje, false ak neexistuje.
     */
    fun employeeExistsByUsername(userName: String): Mono<Boolean>
}