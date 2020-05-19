package fei.stuba.gono.kotlin.nonblocking.services

import fei.stuba.gono.kotlin.pojo.Client
/***
 * Interface for marshalling and de-marshalling Client entities.
 * Rozhranie na marshalling a de-marshalling entitít typu Client.*/
interface ClientService {
    /***
     * Finds the entity with the given id.
     * Nájde entitu so zadaným id.
     * @param id id of the entity, must not be null.
     *           id entity, nesmie byť null.
     * @return entity or null if no entity was found.
     * entita alebo null ak entitu nie je možné nájsť.
     */
    suspend fun getClientById(id: String): Client?

    /***
     * Checks if the entity with the given id was found.
     * Skontroluje, či entita so zadaným id existuje.
     * @param id id of the entity, must not be null.
     *           id entity, nesmie byť null.
     * @return true if the entity was found,
     * false otherwise.
     * true ak entita existuje, false inak.
     */
    suspend fun clientExistsById(id: String): Boolean
}