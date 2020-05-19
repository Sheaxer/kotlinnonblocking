package fei.stuba.gono.kotlin.nonblocking.validation

import fei.stuba.gono.kotlin.pojo.AccountNO
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

/***
 * Class implementing validation of AccountNO - must have either a IBAN or Local Account Number,
 * otherwise the validator rejects the AccountNO with error message "INVALID_ACCOUNT".
 * Trieda implementuje validáciu objektu triedy AccountNO - musí obsahovať
 * buď IBAN alebo lokálne číslo účtu, inak validátor zamietne objekt s chybovou správou "INVALID_ACCOUNT".
 */
@Component
class AccountValidator: Validator {
    override fun validate(p0: Any, p1: Errors) {
        val account: AccountNO = p0 as AccountNO
        if(account.iban.isNullOrBlank())
        {
            if(account.localAccountNumber.isNullOrBlank())
                p1.reject("SOURCEACCOUNT_INVALID")
        }
    }

    override fun supports(p0: Class<*>): Boolean {
        return AccountNO::class.java.isAssignableFrom(p0)
    }
}