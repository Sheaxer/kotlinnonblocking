package fei.stuba.gono.kotlin.nonblocking.validation

import fei.stuba.gono.kotlin.pojo.Money
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class MoneyValidator: Validator {

    /***
     * Limit of amount in ReportedOverlimitTransaction, property
     * - reportedOverlimitTransaction.limit, default 999999999.99
     */
    @Value("\${reportedOverlimitTransaction.limit:999999999.99}")
    private val limit = 999999999.99

    /***
     * Max amount used in ReportedOverlimitTransaction, property
     * - reportedOverlimitTransaction.maxAmount, default 999999999.99
     */
    @Value("\${reportedOverlimitTransaction.maxAmount:999999999.99}")
    private val maxAmount = 999999999.99

    override fun validate(p0: Any, p1: Errors) {
        val m = p0 as  Money
        if(m.currency == null)
            p1.rejectValue("currency","CURRENCY_INVALID")
        if(m.amount == null || m.amount!! <=0 ||m.amount!! > maxAmount)
            p1.reject("FIELD_INVALID")
        else if (m.amount!!>this.limit)
            p1.reject("LIMIT_EXCEEDED")
    }

    override fun supports(p0: Class<*>): Boolean {
        return Money::class.java.isAssignableFrom(p0)
    }
}