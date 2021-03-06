package fei.stuba.gono.kotlin.nonblocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.OrganisationUnit
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
/***
 * Interface extending ReactiveCrudRepository for OrganisationUnit entities. Automatically
 * instantiated by Spring.
 * Rozhranie rozširujúce ReactiveCrudRepository pre entity triedy OrganisationUnit. Automaticky
 * inštanciované pomocou Spring.
 * @see OrganisationUnit
 * @see org.springframework.data.repository.reactive.ReactiveCrudRepository
 */
@Repository
interface OrganisationUnitRepository : ReactiveCrudRepository<OrganisationUnit,String> {}