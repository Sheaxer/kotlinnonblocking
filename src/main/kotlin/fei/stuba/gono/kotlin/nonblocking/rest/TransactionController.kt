package fei.stuba.gono.kotlin.nonblocking.rest

import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionBadRequestException
import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionNotFoundException
import fei.stuba.gono.kotlin.nonblocking.errors.ReportedOverlimitTransactionValidationException
import fei.stuba.gono.kotlin.nonblocking.pojo.ReportedOverlimitTransaction
import fei.stuba.gono.kotlin.nonblocking.services.ReportedOverlimitTransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
/***
 * REST controller for GET, POST, PUT and DELETE methods for
 * ReportedOverlimitTransaction resource. Attached to the endpoints that start with
 * /reportedOverlimitTransaction .
 * REST kontrolér pre GET, POST, PUT a DELETE metódy nad zdrojom triedy
 * ReportedOverlimitTransaction. Pripojený k endpointom začínajúcim s /reportedOverlimitTransaction .
 * @see ReportedOverlimitTransaction
 * @param transactionService Service that provides GET, PUT, POST and DELETE operations on ReportedOverlimitTransaction
 * entities. Služba ktorá poskytuje GET, PUT, POST a DELETE operácie nad entitami triedy
 * ReportedOverlimitTransaction.
 */
@RestController
@RequestMapping("/reportedOverlimitTransaction")
class TransactionController @Autowired constructor(
        private val transactionService: ReportedOverlimitTransactionService){

    /***
     * GET method - retrieves the entity with the given id.
     * GET metóda - načíta entitu so zadaným id.
     * @param id id of requested entity, must not be null.
     *           id požadovanej entity, nesmie byť null.
     * @return entity.
     * žiadaná entita
     * @throws ReportedOverlimitTransactionNotFoundException exception thrown
     * if there is no entity with the given id. výnimka
     * vyvolaná, ak entita so zadaným id neexistuje.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    suspend fun getTransaction(@PathVariable id : String ) : ReportedOverlimitTransaction
    {
        return transactionService.getTransactionById(id) ?: throw
        ReportedOverlimitTransactionNotFoundException("ID_NOT_FOUND")
    }

    /***
     * POST method - validates the entity and if valid -
     * generates new id and saves the entity.
     * POST metóda - validuje entitu a ak je korektná - generuje nové id a uloží entitu.
     * @param newTransaction entity to be saved, must not be null.
     *                       entita, ktorá sa má uložiť, nesmie byť null.
     * @return the saved entity.
     * uložená entita.
     * @throws ReportedOverlimitTransactionValidationException  exception containing validation
     * error codes, thrown if the validation of entity fails.
     * výnimka obsahujúca chybné validačné hlášky, vyvolaná ak je validácia neúspešná.
     */
    @PostMapping(consumes = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    @Throws(ReportedOverlimitTransactionValidationException::class)
    suspend fun postTransaction(@RequestBody newTransaction: ReportedOverlimitTransaction) :
            ReportedOverlimitTransaction
    {
        return transactionService.postTransaction(newTransaction)
    }

    /***
     * PUT method - validates the entity and if valid - saves it using the given id.
     * PUT metóda - validuje entitu a ak je korektná - uloží ju so zadaným id.
     * @param id id which will be identifying the saved entity.
     *           id ktoré bude identifikovať uloženú entitu.
     * @param transaction entity to be saved.
     *                    entita, ktorá sa má uložiť.
     * @return the saved entity.
     * uložená entitu.
     * @throws ReportedOverlimitTransactionValidationException exception containing the validation
     * error codes, thrown if the validation of entity fails.
     * výnimka obsahujúca validačné chybové hlášky, ktorá je vyvolaná ak je validácia entity
     * neúspešná.
     */
    @PutMapping("/{id}", consumes = ["application/json"])
    @PostMapping("/{id}", consumes = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    @Throws(ReportedOverlimitTransactionValidationException::class)
    suspend fun putTransaction(@PathVariable id: String, @RequestBody transaction: ReportedOverlimitTransaction)
    : ReportedOverlimitTransaction
    {
        return transactionService.putTransaction(id,transaction)
    }

    /***
     * DELETE method - that deletes the entity with given id.
     * DELETE metóda - zmaže entitu so zadaným id.
     * @param id id of entity that should be deleted.
     *           id entity ktorá by mala byť zmazaná
     * @throws ReportedOverlimitTransactionNotFoundException exception
     * thrown if no entity with the given id was found.
     * výnimka vyhodená ak nebola nájdená entita so zadaným id.
     * @throws ReportedOverlimitTransactionBadRequestException exception thrown
     * if entity with the given id couldn't be deleted.
     * výnimka vyvolaná ak entita so zadaným id nemohla byť vymazaná.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Throws(ReportedOverlimitTransactionBadRequestException::class,
            ReportedOverlimitTransactionNotFoundException::class)
    suspend fun deleteTransaction(@PathVariable id:String)
    {
       return transactionService.deleteTransaction(id)
    }

    /*@PostMapping("/{id}",consumes = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    suspend fun postTransaction(@PathVariable id: String, @RequestBody transaction: ReportedOverlimitTransaction):
            ReportedOverlimitTransaction
    {
        return transactionService.putTransaction(id,transaction)
    }*/
}