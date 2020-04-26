package fei.stuba.gono.kotlin.pojo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/***
 * Class holding data about organisation unit.
 */
@Document(collection = "organisationUnits")
data class OrganisationUnit (

    @Id
    var id: String? = null
    )