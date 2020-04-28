package fei.stuba.gono.kotlin.nonblocking.mongo.services

import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionBadRequestException
import fei.stuba.gono.kotlin.nonblocking.errors.ReportedOverlimitTransactionValidationException
import fei.stuba.gono.kotlin.nonblocking.mongo.repositories.EmployeeRepository
import fei.stuba.gono.kotlin.nonblocking.services.EmployeeService
import fei.stuba.gono.kotlin.nonblocking.validation.EmployeeValidator
import fei.stuba.gono.kotlin.pojo.Employee
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.validation.BeanPropertyBindingResult

/***
 * Implementation of EmployeeService for use with MongoDB in nonblocking mode. Uses kotlin coroutines.
 */
@Service
class EmployeeServiceImpl @Autowired constructor(
        private val employeeRepository: EmployeeRepository,
        private val employeeValidator: EmployeeValidator,
        private val nextSequenceService: NextSequenceService,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder)
    : EmployeeService {



    @Value("\${reportedOverlimitTransaction.employee.sequenceName:employeeSequence}")
    private val sequenceName : String = "employeeSequence"

    override suspend fun findEmloyeeByUsername(userName: String): Employee? {
        return employeeRepository.findEmployeeByUsername(userName).awaitFirstOrNull()
    }

    override suspend fun saveEmployee(employee: Employee): Employee? {
        validate(employee)
        if(employeeRepository.existsByUsername(employee.username!!).awaitFirst())
            throw ReportedOverlimitTransactionBadRequestException("USERNAME_EXISTS")
        employee.id = nextSequenceService.getNewId(employeeRepository,sequenceName)
        employee!!.password = bCryptPasswordEncoder.encode(employee.password)
        return employeeRepository.save(employee).awaitFirst()

    }

    override suspend fun employeeExistsById(id: String): Boolean {
        return employeeRepository.existsById(id).awaitFirstOrElse { false }
    }

    override suspend fun validate(employee: Employee)
    {
        val errors = BeanPropertyBindingResult(employee,Employee::class.java.name)
        employeeValidator.validate(employee,errors)
        if(errors.hasErrors())
        {
            throw ReportedOverlimitTransactionValidationException(
                    errors.allErrors.map { objectError ->
                        objectError.codes!![objectError!!.codes!!.lastIndex]
                    }.toMutableList()
            )
        }
    }
}