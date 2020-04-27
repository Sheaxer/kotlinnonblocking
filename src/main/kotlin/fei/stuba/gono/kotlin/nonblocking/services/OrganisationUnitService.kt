package fei.stuba.gono.kotlin.nonblocking.services

import fei.stuba.gono.kotlin.pojo.OrganisationUnit

/***
 * Interface for marshalling and de-marshalling OrganisationUnit entities.
 */
interface OrganisationUnitService {

    suspend fun getOrganisationUnitById(id: String): OrganisationUnit?

    suspend fun organisationUnitExistsById(id: String): Boolean
}