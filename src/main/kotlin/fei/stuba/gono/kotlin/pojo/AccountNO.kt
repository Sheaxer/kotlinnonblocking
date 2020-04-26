package fei.stuba.gono.kotlin.pojo

import com.fasterxml.jackson.annotation.JsonInclude

/***
 * Class representing AccountNO - Account number of the client
 * (type: IBAN with optional BIC or local account number) where withdraw will be realised
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class AccountNO(

        var iban: String? = null,
        var bic: String? = null,
        var localAccountNumber: String? = null)