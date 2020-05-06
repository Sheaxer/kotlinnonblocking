package fei.stuba.gono.kotlin.nonblocking.rest

import fei.stuba.gono.kotlin.nonblocking.services.EmployeeService
import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * TODO
 *
 * @property employeeService
 */
@RestController
class SignUpController @Autowired constructor(private val employeeService: EmployeeService){

    @PostMapping("/signup", consumes = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    suspend fun postEmployee(@RequestBody employee: Employee): String
    {
        employeeService.saveEmployee(employee)
        return "SUCCESFULLY_REGISTERED"
    }
}