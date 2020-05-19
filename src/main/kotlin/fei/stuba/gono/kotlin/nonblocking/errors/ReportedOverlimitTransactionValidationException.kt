package fei.stuba.gono.kotlin.nonblocking.errors

import java.lang.RuntimeException
/**
 * Custom Exception class to be thrown during validation of
 * entity payload of PUT and POST REST methods.
 * Vlastná výnimka ktorá sa vyvoláva počas validácie entity ktorá je dátovým obsahom
 * požiadavky pre PUT A POST REST metód.
 */
class ReportedOverlimitTransactionValidationException() : RuntimeException() {
    var errors: MutableList<String>? = null

    /**
     * Constructor that creates the exception with list of error messages.
     * Konštruktor ktorý vytvára výnimku pomocou listu chybných hlášok.
     * @param errors list of error messages.
     *               list chybných hlášok.
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
