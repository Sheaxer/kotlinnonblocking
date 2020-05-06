package fei.stuba.gono.kotlin.nonblocking.errors

import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionBadRequestException
import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import fei.stuba.gono.kotlin.nonblocking.validation.ReportedOverlimitTransactionValidator
import java.util.*
/***
 * <div class="en">Class that implements custom error handling.</div>
 * <div class="sk">Trieda ktorá implementuje vlastné spravocanie výnimiek.</div>
 */
@RestControllerAdvice
class ErrorHandler {

    /***
     * <div class="en">Handles validation errors that occur during put and post REST methods. Returns
     * HTTTp code BAD_REQUEST - 400 and list of validation errors.</div>
     * <div class="sk">Spracuváva validačné výnimky ktoré môžu nastať počas PUT a POST REST metód. Vracia
     * HTTP kód BAD_REQUEST - 400 a zoznam validačných chýb v tele odpovede.</div>
     * @see ReportedOverlimitTransactionValidationException
     * @see stuba.fei.gono.java.nonblocking.validation.ReportedOverlimitTransactionValidator
     * @param ex <div class="en">exception containing errors that were discovered during validation.</div>
     *           <div class="sk">výnimka obsahujúca zoznam chybných hlášok zistených počas validácie.</div>
     * @return <div class="en">Mono emitting a List of errors discovered during validation.</div>
     * <div class="sk">Mono emitujúce zoznam chybových hlášok zistených počas validácie.</div>
     *
     */
    @ExceptionHandler(ReportedOverlimitTransactionValidationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    suspend fun handleReportedOverlimitTransactionValidationException(
            ex : ReportedOverlimitTransactionValidationException) : MutableList<String>?
    {
        return ex.errors
    }

    /***
     * <div class="en">Handles ReportedOverlimitTransactionNotFoundException
     * by returning the error code and sending HTTP code NOT_FOUND - 404.</div>
     * <div class="sk">Spracúváva ReportedOverlimitTransactionNotFoundException výnimku
     * vrátením HTTP kódu 404 Not Found a chybovej hlášky v tele správy.</div>
     * @param ex <div class="en">caught exception.</div>
     *           <div class="sk">odchytená výnimka.</div>
     * @return <div class="en">Mono emitting the list containing the error message of ex.</div>
     * <div class="sk">Mono emitujúce zoznam obsahujúcí chybovú hlášku v ex.</div>
     */
    @ExceptionHandler(ReportedOverlimitTransactionNotFoundException::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    suspend fun handleNotFound(ex: Exception):  ArrayList<String?>{
        return ArrayList(setOf(ex.message))
    }

    /***
     * <div class="en">Handles ReportedOverlimitTransactionBadRequestException by returning
     * the error code and sending HTTP code BAD_REQUEST - 400.</div>
     * <div class="sk">Spracúvava ReportedOverlimitTransactionBadRequestException výnimku vrátením
     * HTTP kódu BAD_REQUEST - 400 a chybovej hlášky v tele správy.</div>
     * @param ex <div class="en">caught exception.</div>
     *           <div class="sk">odchytená výnimka.</div>
     * @return <div class="en">Mono emitting the list containing the error message of ex.</div>
     * <div class="sk">Mono emitujúce zoznam ktorý obsahuje chybovú hlášku v ex.</div>
     */
    @ExceptionHandler(ReportedOverlimitTransactionBadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    suspend fun handleBadRequest(ex: ReportedOverlimitTransactionBadRequestException): ArrayList<String?> {
        return ArrayList(setOf(ex.message))
    }
}