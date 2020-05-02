package fei.stuba.gono.kotlin.errors

import java.lang.RuntimeException

/***
 * Custom exception thrown when cannot perform operation on entity.
 *
 * Vlastná výnimka vyvolaná v prípade, že nebolo možné vykonať operáciu nad entitou.
 */
class ReportedOverlimitTransactionBadRequestException(message: String?)  : RuntimeException(message) {
    
}