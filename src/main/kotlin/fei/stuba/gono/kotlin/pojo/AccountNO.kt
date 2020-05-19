package fei.stuba.gono.kotlin.pojo

import com.fasterxml.jackson.annotation.JsonInclude

/***
 * Class representing AccountNO - Account number of the client
 * (type: IBAN with optional BIC or local account number) where withdraw will be realised.
 * Trieda reprezentujúca AccountNO - číslo bankového účtu (typ: IBAN s voliteľným BIC alebo
 * lokálne číslo účtu).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class AccountNO(

        var iban: String? = null,
        var bic: String? = null,
        var localAccountNumber: String? = null)