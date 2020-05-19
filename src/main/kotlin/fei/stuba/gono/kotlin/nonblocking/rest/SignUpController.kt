package fei.stuba.gono.kotlin.nonblocking.rest

import fei.stuba.gono.kotlin.nonblocking.services.EmployeeService
import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/***
 * REST controller allowing to register new employees - users of the system.
 * REST kontrolér umožňujúci registrovať nových zamestnancov - používateľov systému.
 * @param employeeService Service allowing to retrieve and save entities.
 * Služba umožňujúca získať a uložiť entity.
 */
@RestController
class SignUpController @Autowired constructor(private val employeeService: EmployeeService){

    /***
     * POST method attached to the endpoint /signup. Validates the entity and then saves it
     * if valid.
     * POST metóda pripojená na endpoint /signup. Validuje entitu a ak je korektná, uloží ju.
     * @param employee entity representing new employee to be registered as a user of the system.
     *             entita reprezentujúca nového zamestnanca ktorý má byť zaregistrovaný ako
     *             nový používateľ systému.
     * @return entity not containing password if entity was found, null otherwise.
     * entita bez hesla, ak bola nájdená, inak null.
     */
    @PostMapping("/signup", consumes = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    suspend fun postEmployee(@RequestBody employee: Employee): Employee?
    {
       return employeeService.saveEmployee(employee)
    }
}