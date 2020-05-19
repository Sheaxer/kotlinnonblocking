package fei.stuba.gono.kotlin.pojo

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotBlank

/***
 * Class representing data about bank employee and the system user.
 * Trieda reprezentujúca dáta o zamestnanca banky, ktorý je zároveň používateľom systému.
 */
@Document(collection = "employees")
data class Employee (
    @Id
    var id: String? = null,

    @Indexed(unique = true)
    @get:NotBlank(message="USERNAME_INVALID")
    var username:String? = null,

    @get:NotBlank(message = "PASSWORD_INVALID")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password:String? = null
)