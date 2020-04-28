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
 */
@RestControllerAdvice
class ErrorHandler {

    /***
     * Method that handles validation errors during put and post REST methods
     * @see ReportedOverlimitTransactionValidationException
     * @see ReportedOverlimitTransactionValidator
     * @param ex exception containing errors that were discovered during validation
     * @return Mono from List of errors discovered during validation
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
     * Handles ReportedOverlimitTransactionNotFoundException  by returning the error code and sending HTTP code
     * NOT_FOUND - 404.
     * @param ex caught exception.
     * @return List containing the error message of ex.
     */
    @ExceptionHandler(ReportedOverlimitTransactionNotFoundException::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    suspend fun handleNotFound(ex: Exception):  ArrayList<String?>{
        return ArrayList(setOf(ex.message))
    }

    /***
     * Handles ReportedOverlimitTransactionBadRequestException by returning the error code and sending HTTP code
     * BAD_REQUEST - 400.
     * @param ex caught exception.
     * @return List containing the error message of ex.
     */
    @ExceptionHandler(ReportedOverlimitTransactionBadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    suspend fun handleBadRequest(ex: ReportedOverlimitTransactionBadRequestException): ArrayList<String?> {
        return ArrayList(setOf(ex.message))
    }
}