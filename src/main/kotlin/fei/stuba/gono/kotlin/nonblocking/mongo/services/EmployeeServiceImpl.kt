package fei.stuba.gono.kotlin.nonblocking.mongo.services

import fei.stuba.gono.kotlin.nonblocking.mongo.repositories.EmployeeRepository
import fei.stuba.gono.kotlin.nonblocking.services.EmployeeService
import fei.stuba.gono.kotlin.pojo.Employee
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmployeeServiceImpl @Autowired constructor(private val employeeRepository: EmployeeRepository)
    : EmployeeService {
    override suspend fun findEmloyeeByUsername(userName: String): Employee? {
        return employeeRepository.findEmployeeByUsername(userName).awaitFirstOrNull()
    }

    override suspend fun saveEmployee(employee: Employee): Employee? {
        return employeeRepository.save(employee).awaitFirst()
    }

    override suspend fun employeeExistsById(id: String): Boolean {
        return employeeRepository.existsById(id).awaitFirstOrElse { false }
    }
}