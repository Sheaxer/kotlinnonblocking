package fei.stuba.gono.kotlin.nonblocking.validation

import fei.stuba.gono.kotlin.pojo.Employee
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator
/***
 * Class implementing the Validator interface - checks if the Employee instance contains
 * both user name and password. Rejects the object with error code "USERNAME_INVALID" if the user name is missing
 * and "PASSWORD_INVALID" if password is missing.
 * Trieda implementuje rozhranie Validator - skontroluje, či inštancia triedy Employee
 * obsahuje zároveň používateľské meno a heslo. Odmietne inštanciu s chybovým kódom "USERNAME_INVALID"
 * ak inštancia neobsahuje používateľské meno a kód "PASSWORD_INVALID" ak chýba heslo.
 */
@Component
class EmployeeValidator: Validator {
    override fun validate(p0: Any, p1: Errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(p1,"username","USERNAME_INVALID")
        ValidationUtils.rejectIfEmptyOrWhitespace(p1,"password","PASSWORD_INVALID")
    }

    override fun supports(p0: Class<*>): Boolean {
        return Employee::class.java.isAssignableFrom(p0)
    }
}