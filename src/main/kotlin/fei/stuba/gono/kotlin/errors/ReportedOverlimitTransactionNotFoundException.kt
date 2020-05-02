package fei.stuba.gono.kotlin.errors

import java.lang.RuntimeException

/***
 * Custom Exception thrown when ReportedOverlimitTransaction entity is not found.
 *
 * Vlasntá výnimka vyvolaná keď entita nebola nájdená.
 */
class ReportedOverlimitTransactionNotFoundException(message: String?) : RuntimeException(message) {

}