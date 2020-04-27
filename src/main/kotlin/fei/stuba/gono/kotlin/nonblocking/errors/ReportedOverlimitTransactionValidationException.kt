package fei.stuba.gono.kotlin.nonblocking.errors

import java.lang.RuntimeException

class ReportedOverlimitTransactionValidationException() : RuntimeException() {
    var errors: MutableList<String>? = null
    constructor(errors: MutableList<String>) : this() { this.errors = errors }

    fun addError(error: String)
    {
        if(this.errors == null)
            this.errors = mutableListOf(error)
        else
            this.errors!!.add(error)
    }
    }
