package fei.stuba.gono.kotlin.nonblocking.services

import fei.stuba.gono.kotlin.pojo.Account
/***
 * Interface for marshalling and de-marshalling Account entities.
 */
interface AccountService {
    /***
     * Retrieve entity identified by the given IBAN.
     * @param iban IBAN identifying the entity.
     * @return Mono emitting the entity or Mono.empty() if no entity was found.
     */
    suspend fun getAccountByIban(iban: String) : Account?

    /***
     * Retrieve entity identified by the given Local Account Number.
     * @param number Local Account Number identifying the entity.
     * @return Mono emitting the entity or Mono.empty() if no entity was found.
     */
    suspend fun getAccountByLocalAccountNumber(number: String): Account?
}