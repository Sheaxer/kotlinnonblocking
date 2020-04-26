package fei.stuba.gono.kotlin.pojo

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotBlank

/***
 * Class representing data about bank employee and the system user.
 */
@Document(collection = "employees")
data class Employee (
    @Id
    var id: String? = null,

    @Indexed(unique = true)
    @NotBlank
    var username:String? = null,

    @NotBlank
    @JsonIgnore
    var password:String? = null
)