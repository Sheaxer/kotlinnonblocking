package fei.stuba.gono.kotlin.nonblocking.mongo.repositories

import fei.stuba.gono.kotlin.nonblocking.pojo.ReportedOverlimitTransaction
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
/**
 * Interface extending ReactiveCrudRepository for ReportedOverlimitTransaction entities.
 * Automatically instantiated by Spring.
 * Rozhranie rozširujúce ReactiveCrudRepository pre entity triedy ReportedOverlimitTransaction.
 *  Automaticky inštanciované pomocou Spring.
 * @see ReportedOverlimitTransaction
 * @see org.springframework.data.repository.reactive.ReactiveCrudRepository
 */
@Repository
interface ReportedOverlimitTransactionRepository : ReactiveCrudRepository<ReportedOverlimitTransaction,String>
{}