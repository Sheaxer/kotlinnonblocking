package fei.stuba.gono.kotlin.nonblocking.errors

import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionBadRequestException
import fei.stuba.gono.kotlin.errors.ReportedOverlimitTransactionNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import reactor.core.publisher.Mono
import java.util.*

@RestControllerAdvice
class ErrorHandler {

    @ExceptionHandler(ReportedOverlimitTransactionValidationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    suspend fun handleReportedOverlimitTransactionValidationException(
            ex : ReportedOverlimitTransactionValidationException) : MutableList<String>?
    {
        return ex.errors
    }

    @ExceptionHandler(ReportedOverlimitTransactionNotFoundException::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    suspend fun handleNotFound(ex: Exception):  ArrayList<String?>{
        return ArrayList(setOf(ex.message))
    }

    @ExceptionHandler(ReportedOverlimitTransactionBadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    suspend fun handleBadRequest(ex: ReportedOverlimitTransactionBadRequestException): ArrayList<String?> {
        return ArrayList(setOf(ex.message))
    }
}