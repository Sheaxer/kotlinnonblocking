package fei.stuba.gono.kotlin.nonblocking.errors

import java.lang.RuntimeException
/***
 * <div class="en">Custom Exception class to be thrown during validation of
 * entity payload of PUT and POST REST methods.</div>
 * <div class="sk">Vlastná výnimka ktorá sa vyvoláva počas validácie entity ktorá je dátovým obsahom
 * požiadavky pre PUT A POST REST metód.</div>
 * @see stuba.fei.gono.java.nonblocking.services.ReportedOverlimitTransactionService
 */
class ReportedOverlimitTransactionValidationException() : RuntimeException() {
    var errors: MutableList<String>? = null

    /***
     * <div class="en">Constructor that creates the exception with list of error messages.</div>
     * <div class="sk">Konštruktor ktorý vytvára výnimku pomocou listu chybných hlášok.</div>
     * @param errors <div class="en">list of error messages.</div>
     *               <div class="sk">list chybných hlášok.</div>
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
