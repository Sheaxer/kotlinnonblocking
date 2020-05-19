package fei.stuba.gono.kotlin.nonblocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.Account
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

/***
 * Interface extending ReactiveCrudRepository for Account entities. Automatically
 * instantiated by Spring
 * Rozhranie rozširujúce ReactiveCrudRepository pre entity triedy Account. Automaticky inštanciované
 * pomocou Spring.
 * @see Account
 * @see org.springframework.data.repository.reactive.ReactiveCrudRepository
 */
@Repository
interface AccountRepository : ReactiveCrudRepository<Account,String> {
    /***
     * Finds the entity identified by the given IBAN.
     * Nájde entitu identifikovanú zadaným IBAN-om.
     * @param iban - IBAN identifying the Account entity, must not be null.
     *             IBAN identikujúci entitu triedy Account, nemôže byť null.
     * @return Mono emitting the entity or Mono.empty() if no entity is found.
     * Mono emitujúce entitu alebo Mono.empty() ak nebolo možné entitu nájsť.
     */
    fun findAccountByIban(iban: String): Mono<Account?>

    /***
     * Finds the entity identified by the given Local Account Number.
     * Nájde entitu identifikovanú zadaným lokálnym číslom účtu.
     * @param number - Local Account Number identifying the Account entity, must not be null.
     *               lokálne číslo účtu identifikujúce entitu triedy Account, nesmie byť null.
     * @return Mono emitting the entity or Mono.empty() if no entity is found.
     * Mono emitujúce entitu alebo Mono.empty() ak nebolo možné entitu nájsť.
     */
     fun findAccountByLocalAccountNumber(number: String): Mono<Account?>
}