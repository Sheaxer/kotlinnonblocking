package fei.stuba.gono.kotlin.pojo

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotBlank

/***
 * Class representing data about a bank client.
 */
@Document(value = "clients")
@TypeAlias(value = "client")
data class Client (
    @NotBlank
    var firstName: String? = null,
    @NotBlank
    var surName: String? = null,

    @Id
    var id: String? = null
)