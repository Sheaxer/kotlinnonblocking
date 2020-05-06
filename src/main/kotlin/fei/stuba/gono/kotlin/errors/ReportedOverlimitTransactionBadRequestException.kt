package fei.stuba.gono.kotlin.errors

import java.lang.RuntimeException

/***
 * <div class="en">Custom exception when cannot perform operation on ReportedOverlimitTransaction entity.</div>
 * <div class="sk">Vlastná výnimka vyvolaná v prípade, že nebolo možné vykonať operáciu nad entitou.</div>
 */
class ReportedOverlimitTransactionBadRequestException(message: String?)  : RuntimeException(message) {
    
}