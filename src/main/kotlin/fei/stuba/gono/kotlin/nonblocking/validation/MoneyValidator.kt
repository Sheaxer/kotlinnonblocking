package fei.stuba.gono.kotlin.nonblocking.validation

import fei.stuba.gono.kotlin.pojo.Money
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
/***
 * Class implementing the validation of Amount of money on ReportedOverlimitTransaction.
 * Property amount must be greater than
 * 0 and less than maxAmount or validator rejects with error code "FIELD_INVALID", and
 * the amount must be less than limit or validator rejects with error code "LIMIT_EXCEEDED".
 * Trieda implementuje Validator rozhranie a validáciu inštancií triedy Money.
 * Premenná amount musí byť viac než 0 a menej než hodnota maxAmount - inak validátor zamietne inštanciu s chybovým
 * kódom "FIELD_INVALID" a amount musí byť menej než hodnota limit - inak validátor
 * zamietne inštanciu s chybovám kódom "LIMIT_EXCEEDED".
 */
@Component
class MoneyValidator: Validator {

    /***
     * Limit of amount in ReportedOverlimitTransaction, retrieved from property
     * - reportedOverlimitTransaction.limit, default 999999999.99
     * Limit množstva peňazí v ReportedOverlimitTransaction, získané z atribút
     *  reportedOverlimitTransaction.limit, predvolená hodnota 999999999.99.
     */
    @Value("\${reportedOverlimitTransaction.limit:999999999.99}")
    private val limit = 999999999.99

    /***
     * Max amount used in ReportedOverlimitTransaction, retrieved from  property
     * - reportedOverlimitTransaction.maxAmount, default 999999999.99
     * Maximálna možná hodnota peňazí v objekte triedy ReportedOverlimitTransaction,
     * získané z atribútú reportedOverlimitTransaction.maxAmount s predvolenou hodnotou 999999999.99.
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