package fei.stuba.gono.kotlin.nonblocking.services

import fei.stuba.gono.kotlin.pojo.Account
/***
 * Interface for marshalling and de-marshalling Account entities.
 * Rozhranie na marhalling a de-marshalling entít triedy Account.
 */
interface AccountService {
    /***
     * Finds the entity identified by the given IBAN.
     * Nájde entitu identifikovanú zadaným IBAN-om.
     * @param iban IBAN identifying the entity.
     *             IBAN identifikujúci entitu.
     * @return the entity or null if no entity was found.
     * entita alebo null ak entita neexistuje.
     */
    suspend fun getAccountByIban(iban: String) : Account?

    /***
     * Finds entity identified by the given Local Account Number.
     * Nájde entitu identifikovanú lokálnym číslom účtu.
     * @param number Local Account Number identifying the entity.
     *               Lokálne číslo účtu identifikujúce entitu.
     * @return  the entity or null if no entity was found.
     * entita alebo null ak žiadna entita nebola nájdená.
     */
    suspend fun getAccountByLocalAccountNumber(number: String): Account?
}