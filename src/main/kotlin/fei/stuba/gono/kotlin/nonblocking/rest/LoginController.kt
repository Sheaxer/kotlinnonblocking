package fei.stuba.gono.kotlin.nonblocking.rest

import fei.stuba.gono.kotlin.nonblocking.services.EmployeeService
import fei.stuba.gono.kotlin.pojo.Employee
import fei.stuba.gono.kotlin.nonblocking.security.JwtUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
/***
 * Rest controller that generates JWT that allows access to REST
 * resources upon successfully providing correct username and corresponding password.
 * Rest kontrolér ktorý generuje JWT ktorý umožňuje prístup k REST
 * prostriedkom po úspešnom zadaní používateľského mena a zodpovedajúceho hesla.
 * @param bCryptPasswordEncoder
 * Password encoder used to encode the given password
 * to check if is the same as stored password.
 * Enkóder hesiel použitý na zakódovanie doručeného hesla ktoré sa porovná s uloženým
 * heslom.
 * @param employeeService
 * Service used to retrieve and save Employee entities.
 * Služba použitá na získanie a uloženie entít triedy Employee.
 */
@RestController
class LoginController @Autowired constructor(
        private val bCryptPasswordEncoder: BCryptPasswordEncoder,
        private val employeeService: EmployeeService
) {
    /***
     * Rest POST method on /login endpoint. If HTTP request contains valid entity with correct
     * username and corresponding passwords returns a JWT that allows
     * access to resources in the Authorization header of Http response.
     * Rest POST metóda na /login endpointe. Ak obsah HTTP požiadavky obsahuje validnú
     * entitu triedy Employee s koretkným používateľským menom a správnym korešpondujúcim heslom
     * HTTP odvoveď bude v hlavičke Authorization obsahovať JWT ktorý umožňuje prístup k REST prostriedkom.
     *
     * @param employee entity representing employee who wants to log into the system and gain
     *                 access to REST resources.
     *                 entita reprezentujúca zamestnanca, ktorý požiada o prihlásenie do systému
     *                 a získať prístup k REST prostriedkom.
     * @return ResponseEntity with HTTP code OK - 200 with the JWT in the Authorization header if the
     * log in was successful,
     * or ResponseEntity with HttpStatus code UNAUTHORIZED - 401 if the log in was unsuccessful
     * ResponseEntity s HTTP kódom OK - 200 a s JWT v Authorization sekcii http hlavičky ak
     * bolo prihlásenie úspešné alebo ResponseEntity s HTTP kódom UNAUTHORIZED - 401 ak prihlásenie zlyhalo.
     */
    @PostMapping("/login",consumes = ["application/json"])
    suspend fun login(@RequestBody employee:Employee): ResponseEntity<Void>
    {
        employeeService.validate(employee)
        val emp = employeeService.findEmployeeByUsername(employee.username!!)
                ?: return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        if(bCryptPasswordEncoder.matches(employee.password,emp.password))
        {
            val responseHeaders = HttpHeaders()
            responseHeaders.set(HttpHeaders.AUTHORIZATION,JwtUtils.createJWT(employee.username!!))
            return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).build()

        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
    }
}