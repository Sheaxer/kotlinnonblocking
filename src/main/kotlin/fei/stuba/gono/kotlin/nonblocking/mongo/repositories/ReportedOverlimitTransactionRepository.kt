package fei.stuba.gono.kotlin.nonblocking.mongo.repositories

import fei.stuba.gono.kotlin.nonblocking.pojo.ReportedOverlimitTransaction
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
/**
 * <div class="en">Interface extending ReactiveCrudRepository for ReportedOverlimitTransaction entities.
 * Automatically instantiated by Spring.</div>
 * <div class="sk">Rozhranie rozširujúce ReactiveCrudRepository pre entity triedy ReportedOverlimitTransaction.
 *  Automaticky inštanciované pomocou Spring.</div>
 * @see ReportedOverlimitTransaction
 * @see org.springframework.data.repository.reactive.ReactiveCrudRepository
 */
@Repository
interface ReportedOverlimitTransactionRepository : ReactiveCrudRepository<ReportedOverlimitTransaction,String>
{}