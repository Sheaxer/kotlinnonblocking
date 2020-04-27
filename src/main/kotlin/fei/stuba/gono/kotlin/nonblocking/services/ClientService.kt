package fei.stuba.gono.kotlin.nonblocking.services

import fei.stuba.gono.kotlin.pojo.Client

interface ClientService {
    /***
     * Retrieve entity by the given id.
     * @param id must not be null
     * @return Mono emitting the entity or Mono.empty() if no entity was found.
     */
    suspend fun getClientById(id: String): Client?

    /***
     * Checks if the entity with the given id was found.
     * @param id must not be null
     * @return Mono emitting true if the entity was found, false otherwise.
     */
    suspend fun clientExistsById(id: String): Boolean
}