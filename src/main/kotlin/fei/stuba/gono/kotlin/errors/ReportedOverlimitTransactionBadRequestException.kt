package fei.stuba.gono.kotlin.errors

import java.lang.RuntimeException

/***
 * Custom exception when cannot perform operation on ReportedOverlimitTransaction entity.
 */
class ReportedOverlimitTransactionBadRequestException(message: String?)  : RuntimeException(message) {
    
}