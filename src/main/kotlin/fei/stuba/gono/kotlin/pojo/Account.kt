package fei.stuba.gono.kotlin.pojo

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
/***
 * Class representing a banking account.
 */
@Document(collection = "accounts")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Account(
        @Indexed(unique = true)
        var iban: String? = null,

        var bic: String? = null,

        @Indexed(unique = true)
        var localAccountNumber: String? = null,

        @JsonIgnore
        var isActive : Boolean? = null,

        @Id
        @JsonIgnore
        var id: String? = null

)