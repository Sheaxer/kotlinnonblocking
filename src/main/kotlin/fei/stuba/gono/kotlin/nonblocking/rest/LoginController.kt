package fei.stuba.gono.kotlin.nonblocking.rest

import fei.stuba.gono.kotlin.nonblocking.services.EmployeeService
import fei.stuba.gono.kotlin.pojo.Employee
import fei.stuba.gono.kotlin.nonblocking.security.JwtUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController @Autowired constructor(
        private val bCryptPasswordEncoder: BCryptPasswordEncoder,
        private val employeeService: EmployeeService
) {
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