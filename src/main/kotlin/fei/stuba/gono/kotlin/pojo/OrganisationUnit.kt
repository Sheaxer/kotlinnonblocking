package fei.stuba.gono.kotlin.pojo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/***
 * Class maintaining data about organisation unit.
 * Trieda, ktorá uchováva dáta o mieste výberu.
 */
@Document(collection = "organisationUnits")
data class OrganisationUnit (

    @Id
    var id: String? = null
    )