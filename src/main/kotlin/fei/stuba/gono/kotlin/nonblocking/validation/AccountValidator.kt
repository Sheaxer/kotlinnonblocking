package fei.stuba.gono.kotlin.nonblocking.validation

import fei.stuba.gono.kotlin.pojo.Account
import fei.stuba.gono.kotlin.pojo.AccountNO
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

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