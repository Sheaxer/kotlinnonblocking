package fei.stuba.gono.kotlin.nonblocking.mongo.services

import fei.stuba.gono.kotlin.nonblocking.mongo.repositories.OrganisationUnitRepository
import fei.stuba.gono.kotlin.nonblocking.services.OrganisationUnitService
import fei.stuba.gono.kotlin.pojo.OrganisationUnit
import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
/***
 * Implementation of OrganisationUnitService for use with MongoDB.
 * @see OrganisationUnitService
 */
@Service
class OrganisationUnitServiceImpl @Autowired constructor(private val organisationUnitRepository:
                                                         OrganisationUnitRepository) : OrganisationUnitService {
    override suspend fun getOrganisationUnitById(id: String): OrganisationUnit? {
        return organisationUnitRepository.findById(id).awaitFirstOrNull()
    }

    override suspend fun organisationUnitExistsById(id: String): Boolean {
        return organisationUnitRepository.existsById(id).awaitFirstOrElse { false }
    }

}