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
 * Class that implements custom error handling.
 * Trieda ktorá implementuje vlastné spravocanie výnimiek.
 */
@RestControllerAdvice
class ErrorHandler {

    /**
     * Handles validation errors that occur during put and post REST methods. Returns
     * HTTTp code BAD_REQUEST - 400 and list of validation errors. Suspending function.
     * Spracuváva validačné výnimky ktoré môžu nastať počas PUT a POST REST metód. Vracia
     * HTTP kód BAD_REQUEST - 400 a zoznam validačných chýb v tele odpovede. Zastaviteľná funkcia.
     * @see ReportedOverlimitTransactionValidationException
     * @see fei.stuba.gono.kotlin.nonblocking.validation.ReportedOverlimitTransactionValidator
     * @param ex exception containing errors that were discovered during validation.
     *           výnimka obsahujúca zoznam chybných hlášok zistených počas validácie.
     * @return Mono emitting a List of errors discovered during validation.
     * Mono emitujúce zoznam chybových hlášok zistených počas validácie.
     *
     */
    @ExceptionHandler(ReportedOverlimitTransactionValidationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    suspend fun handleReportedOverlimitTransactionValidationException(
            ex : ReportedOverlimitTransactionValidationException) : MutableList<String>?
    {
        return ex.errors
    }

    /**
     * Handles ReportedOverlimitTransactionNotFoundException
     * by returning the error code and sending HTTP code NOT_FOUND - 404. Suspending function.
     * Spracúváva ReportedOverlimitTransactionNotFoundException výnimku
     * vrátením HTTP kódu 404 Not Found a chybovej hlášky v tele správy.  Zastaviteľná funkcia.
     * @param ex caught exception.
     *           odchytená výnimka.
     * @return Mono emitting the list containing the error message of ex.
     * Mono emitujúce zoznam obsahujúcí chybovú hlášku v ex.
     */
    @ExceptionHandler(ReportedOverlimitTransactionNotFoundException::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    suspend fun handleNotFound(ex: Exception):  ArrayList<String?>{
        return ArrayList(setOf(ex.message))
    }

    /**
     * Handles ReportedOverlimitTransactionBadRequestException by returning
     * the error code and sending HTTP code BAD_REQUEST - 400. Suspending function.
     * Spracúvava ReportedOverlimitTransactionBadRequestException výnimku vrátením
     * HTTP kódu BAD_REQUEST - 400 a chybovej hlášky v tele správy.  Zastaviteľná funkcia.
     * @param ex caught exception.
     *           odchytená výnimka.
     * @return Mono emitting the list containing the error message of ex.
     * Mono emitujúce zoznam ktorý obsahuje chybovú hlášku v ex.
     */
    @ExceptionHandler(ReportedOverlimitTransactionBadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    suspend fun handleBadRequest(ex: ReportedOverlimitTransactionBadRequestException): ArrayList<String?> {
        return ArrayList(setOf(ex.message))
    }
}