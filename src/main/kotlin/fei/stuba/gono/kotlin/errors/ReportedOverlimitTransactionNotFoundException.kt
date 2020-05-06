package fei.stuba.gono.kotlin.errors

import java.lang.RuntimeException

/***
 * <div class="en">Custom Exception used when ReportedOverlimitTransaction entity is not found.</div>
 * <div class="sk">Vlastná výnimka vyvolaná keď entita nebola nájdená.</div>
 */
class ReportedOverlimitTransactionNotFoundException(message: String?) : RuntimeException(message) {

}