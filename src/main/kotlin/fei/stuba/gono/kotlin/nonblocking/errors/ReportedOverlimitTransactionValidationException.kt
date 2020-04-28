package fei.stuba.gono.kotlin.nonblocking.errors

import java.lang.RuntimeException
/***
 * Custom Exception class to be thrown during validation of ReportedOverlimitTransaction entity payload of PUT and
 * POST REST methods.
 * @see stuba.fei.gono.java.nonblocking.services.ReportedOverlimitTransactionService
 */
class ReportedOverlimitTransactionValidationException() : RuntimeException() {
    var errors: MutableList<String>? = null

    /***
     * Constructor that creates the exception with list of error messages
     * @param errors list of error messages
     */
    constructor(errors: MutableList<String>) : this() { this.errors = errors }

    fun addError(error: String)
    {
        if(this.errors == null)
            this.errors = mutableListOf(error)
        else
            this.errors!!.add(error)
    }
    }
