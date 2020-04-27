package fei.stuba.gono.kotlin.nonblocking.mongo.repositories

import fei.stuba.gono.kotlin.nonblocking.pojo.ReportedOverlimitTransaction
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
/***
 * Interface extending CrudRepository for ReportedOverlimitTransaction.
 * @see ReportedOverlimitTransaction
 * @see CrudRepository
 */
@Repository
interface ReportedOverlimitTransactionRepository : ReactiveCrudRepository<ReportedOverlimitTransaction,String>
{

}