package fei.stuba.gono.kotlin.nonblocking.validation

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.TimeUnit
/***
 * Class implementing Validator interface -
 * validation for transferDate field in ReportedOverlimitTransaction.
 * The date must not be the past
 * or on the same day as the day of creating the ReportedOverlimitTransaction or the validator rejects transferDate
 * with error code "INVALID_DATE_IN_PAST". The date also must be at least cDays number of days
 * in the future from the day of creating ReportedOverlimitTransaction or the validator rejects transferDate
 * with error code "FIELD_INVALID_TOO_NEAR_IN_FUTURE".
 * Trieda implementuje Validator rozhranie - validáciu objektu triedy Date uložený v
 * transferDate atribútu inštancií triedy
 * ReportedOverlimitTransaction. Dátum nesmie byť v minulosti, inak validátor zamietne
 * validáciu s chybovým kódom "INVALID_DATE_IN_PAST". Dátum musí byť taktiež
 * minimále hodnotu cDays počet dní v budúcnosti, inak validátor validáciu zamietne s chybovým kódom
 * "FIELD_INVALID_TOO_NEAR_IN_FUTURE".
 */
@Component
class TransferDateValidator: Validator {

    /***
     * Minimal number of days from day of creating ReportedOverlimitTransaction to transfer day.
     * Retrieved from property
     * reportedOverlimitTransaction.daysBefore, default 3.
     * Minimálny počet dní od dátumu vytvorenia inštancie triedy ReportedOverlimitTransaction
     * do dátumu uvevenom v transferDate atribúte. Hodnota získaná z atribútu
     * reportedOverlimitTransaction.daysBefore s predvolenou hodnotou 3.
     */
    @Value("\${reportedOverlimitTransaction.daysBefore:3}")
    private val cDays: Long = 0

    override fun validate(p0: Any, p1: Errors) {
        val cal = Calendar.getInstance()
        cal.time = Date()
        cal[Calendar.HOUR_OF_DAY] = 0
        cal[Calendar.MINUTE] = 0
        cal[Calendar.SECOND] = 0
        val today = cal.time
        val date = p0 as Date
        if(date.before(today)) {
            p1.reject("INVALID_DATE_IN_PAST")
            return
        }
        val i1 = today.toInstant().truncatedTo(ChronoUnit.DAYS)
        val i2 = date.toInstant().truncatedTo(ChronoUnit.DAYS)
        if(i1 == i2) {
            p1.reject("INVALID_DATE_IN_PAST")
            return
        }
        val diff = date.time - today.time
        if(TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS) < cDays)
            p1.reject("FIELD_INVALID_TOO_NEAR_IN_FUTURE")
    }

    override fun supports(p0: Class<*>): Boolean {
        return Date::class.java.isAssignableFrom(p0)
    }
}