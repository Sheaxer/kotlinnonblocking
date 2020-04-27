package fei.stuba.gono.kotlin.nonblocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.Account
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

/***
 * Interface extending ReactiveMongoRepository for Account entities.
 * @see Account
 * @see org.springframework.data.repository.reactive.ReactiveCrudRepository
 */
@Repository
interface AccountRepository : ReactiveCrudRepository<Account,String> {
    /***
     * Returns entity identified by the given IBAN
     * @param iban - IBAN identifying the Account entity, must not be null.
     * @return Mono emitting the Account or Mono.empty() if no entity is found.
     */
    fun findAccountByIban(iban: String): Mono<Account?>

    /***
     * Returns entity identified by the given Local Account Number
     * @param number - Local Account Number identifying the Account entity, must not be null.
     * @return Mono emitting the Account or Mono.empty() if no entity is found.
     */
     fun findAccountByLocalAccountNumber(number: String): Mono<Account?>
}