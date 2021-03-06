package fei.stuba.gono.kotlin.pojo

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotBlank

/***
 * Class representing data about a bank client.
 * Trieda reprezentujúca dáta o bankovom klientovi.
 */
@Document(value = "clients")
@TypeAlias(value = "client")
data class Client (
    @get:NotBlank
    var firstName: String? = null,
    @get:NotBlank
    var surName: String? = null,

    @Id
    var id: String? = null
)