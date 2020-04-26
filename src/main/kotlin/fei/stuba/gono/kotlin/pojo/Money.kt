package fei.stuba.gono.kotlin.pojo

import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

/***
 * Class representing withdraw amount in defined currency (only EUR for DOMESTIC) and with precision.
 */
data class Money (
    @get:NotNull(message = "CURRENCY_INVALID")
    var currency:Currency?=null,
    var amount: Double? = null
)