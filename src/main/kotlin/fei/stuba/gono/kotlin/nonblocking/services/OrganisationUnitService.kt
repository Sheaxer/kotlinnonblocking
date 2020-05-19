package fei.stuba.gono.kotlin.nonblocking.services

import fei.stuba.gono.kotlin.pojo.OrganisationUnit

/***
 * Interface for marshalling and de-marshalling OrganisationUnit entities.
 * Rozhranie na marshalling a de-marshalling entít triedy OrganisationUnit.
 */
interface OrganisationUnitService {

    /***
     * Finds the entity with the given id.
     * Nájde entitu so zadaným id.
     * @param id id of the entity, must not be null.
     *           id entity, nesmie byť null.
     * @return the entity or null if the entity was not found.
     * entita alebo null ak entita neexistuje.
     */
    suspend fun getOrganisationUnitById(id: String): OrganisationUnit?

    /***
     * Checks if the entity with the given id exists.
     * Skontroluje, či entita so zadaným id existuje.
     * @param id id of the entity, must not be null.
     *           id entity, nesmie byť null.
     * @return true if entity exists, false otherwise.
     * true ak entita existuje, false inak.
     */
    suspend fun organisationUnitExistsById(id: String): Boolean
}