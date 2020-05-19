package fei.stuba.gono.kotlin.errors

import java.lang.RuntimeException

/**
 * Custom exception when cannot perform operation on ReportedOverlimitTransaction entity.
 *
 * Vlastná výnimka vyvolaná v prípade, že nebolo možné vykonať operáciu nad entitou.
 */
class ReportedOverlimitTransactionBadRequestException(message: String?)  : RuntimeException(message) {
    
}