package fei.stuba.gono.kotlin.nonblocking.mongo.repositories

import fei.stuba.gono.kotlin.pojo.OrganisationUnit
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
/***
 * Interface extending CrudRepository for OrganisationUnit.
 * @see OrganisationUnit
 * @see CrudRepository
 */
@Repository
interface OrganisationUnitRepository : ReactiveCrudRepository<OrganisationUnit,String> {}