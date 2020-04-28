package fei.stuba.gono.kotlin.nonblocking.validation

import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator

@Component
class EmployeeValidator: Validator {
    override fun validate(p0: Any, p1: Errors) {
        val e = p0 as Employee
        ValidationUtils.rejectIfEmptyOrWhitespace(p1,"username","USERNAME_INVALID")
        ValidationUtils.rejectIfEmptyOrWhitespace(p1,"password","PASSWORD_INVALID")
    }

    override fun supports(p0: Class<*>): Boolean {
        return Employee::class.java.isAssignableFrom(p0)
    }
}