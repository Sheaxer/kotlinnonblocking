package fei.stuba.gono.kotlin.nonblocking.services

import fei.stuba.gono.kotlin.nonblocking.pojo.ReportedOverlimitTransaction
import fei.stuba.gono.kotlin.nonblocking.errors.ReportedOverlimitTransactionValidationException
import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionBadRequestException
import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionNotFoundException

/***
 * Interface for marshalling and de-marshalling ReportedOverlimitTransaction entities.
 * Rozhranie na marhslling a de-marshalling entít triedy ReportedOverlimitTransaction.
 */
interface ReportedOverlimitTransactionService {

    /***
     * Validates the antity and if it is valid - generates a new id
     * and saves the entity.
     * Validuje entitu a ak je korektná - generuje nové id a uloží entitu.
     * @param transaction entity to be saved.
     *                    entita, ktorá má byť uložená.
     * @return the saved entity if the entity was valid.
     * uložena entita, ak entita bola korektná
     *
     * @throws ReportedOverlimitTransactionValidationException exception containing failed validation
     * error codes if the entity is not valid.
     *  výnimka obsahujúca chybové hlášky neúspešnej validácie.
     */
    @Throws(ReportedOverlimitTransactionValidationException::class)
    suspend fun postTransaction(transaction: ReportedOverlimitTransaction): ReportedOverlimitTransaction

    /***
     * Finds the entity with the given id.
     * Nájde entitu so zadaným id.
     * @param id id of the entity, must not be null.
     *           id entity, nesmie byť null.
     * @return the entity or null if there is none.
     * entita alebo null ak entita neexistuje.
     */
    suspend fun getTransactionById(id: String): ReportedOverlimitTransaction?

    /***
     * Validates the entity and if valid - saves the entity with the given id.
     * Validuje entitu a ak je korektná, uloží entitu so zadaným id.
     * @param id id of the entity.
     *           id entity
     * @param transaction entity to be saved.
     *                    entita, ktorá sa má uložiť.
     * @return the saved entity if it was valid.
     * uložena entitu ak bola entita korektná. a
     * @throws ReportedOverlimitTransactionValidationException exception containing failed
     * validation error codes if the entity is not valid.
     * výnimka, obsahujúca chybové validačné kódy, ak entita
     * nebola korektná.
     */
    @Throws(ReportedOverlimitTransactionValidationException::class)
    suspend fun putTransaction(id: String, transaction: ReportedOverlimitTransaction): ReportedOverlimitTransaction

    /***
     * Deletes the entity wtih by the given id.
     * Zmaže entitu so zadaným id.
     * @param id id of entity to be deleted.
     *           id entity, ktorú treba vymazať.
     * @throws  ReportedOverlimitTransactionNotFoundException exception thrown if the entity with given
     * id was not found výnimka vyvolaná, ak entita so zadaným id neexistuje.
     * @throws ReportedOverlimitTransactionBadRequestException exception thrown if the
     * entity couldn't be deleted because its state is State.CLOSED.
     * v7nimka vyvolan8, ak entita nemôže byť vymazaná,
     * pretože hodnota State je State.CLOSED.
     */
    @Throws(ReportedOverlimitTransactionNotFoundException::class,
            ReportedOverlimitTransactionBadRequestException::class)
    suspend fun deleteTransaction(id: String)

    /***
     * Checks if the entity is valid.
     * Skontroluje či entita je korektná.
     * @param transaction entity to be validated.
     *                    entita, ktorá má byť validovaná.
     * @return Mono emitting that the operation completed or Mono.error() containing
     * ReportedOverlimitTransactionValidationException with the failed validation error codes.
     * Mono emitujúce informáciu, že operácia prebehla ak entita bola korektná alebo
     * Mono.error() obsahujúca ReportedOverlimitTransactionValidationException
     * @throws ReportedOverlimitTransactionValidationException exception containing the failed
     * validation error codes if the entity was not valid.
     * výnimka s validačnými chybnými kódmi vyvolaná, ak entita nebola korektná.
     */
    @Throws(ReportedOverlimitTransactionValidationException::class)
    suspend fun validation(transaction: ReportedOverlimitTransaction)

}