package fei.stuba.gono.kotlin.nonblocking.validation

import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import java.util.*
/***
 * Class implementing Validator interface - checks if date is a valid banking date.
 * Currently any date that is not on weekend is valid.
 * Trieda ktorá implementuje rozhranie Validator - kontroluje, či
 * dátum je platný bankovný deň. V súčasnosi každý deň ktorý nie je víkendový, je platný.
 */
@Component
class BankingDayValidator: Validator {
    override fun validate(p0: Any, p1: Errors) {
        val d = p0 as Date
        val c = Calendar.getInstance()
        c.time = d
        val day = c.get(Calendar.DAY_OF_WEEK)
        if(day == Calendar.SATURDAY || day == Calendar.SUNDAY)
            p1.reject("INVALID_DATE")
    }

    override fun supports(p0: Class<*>): Boolean {
        return Date::class.java.isAssignableFrom(p0)
    }
}