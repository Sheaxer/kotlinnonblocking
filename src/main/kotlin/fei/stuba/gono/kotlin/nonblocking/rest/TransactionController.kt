package fei.stuba.gono.kotlin.nonblocking.rest

import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionNotFoundException
import fei.stuba.gono.kotlin.nonblocking.pojo.ReportedOverlimitTransaction
import fei.stuba.gono.kotlin.nonblocking.services.ReportedOverlimitTransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/reportedOverlimitTransaction")
class TransactionController @Autowired constructor(
        private val transactionService: ReportedOverlimitTransactionService){

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    suspend fun getTransaction(@PathVariable id : String ) : ReportedOverlimitTransaction
    {
        return transactionService.getTransactionById(id) ?: throw
        ReportedOverlimitTransactionNotFoundException("ID_NOT_FOUND")
    }
    @PostMapping(consumes = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    suspend fun postTransaction(@RequestBody newTransaction: ReportedOverlimitTransaction) :
            ReportedOverlimitTransaction
    {
        return transactionService.postTransaction(newTransaction)
    }

    @PutMapping("/{id}", consumes = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    suspend fun putTransaction(@PathVariable id: String, @RequestBody transaction: ReportedOverlimitTransaction)
    : ReportedOverlimitTransaction
    {
        return transactionService.putTransaction(id,transaction)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    suspend fun deleteTransaction(@PathVariable id:String)
    {
       return transactionService.deleteTransaction(id)
    }
}