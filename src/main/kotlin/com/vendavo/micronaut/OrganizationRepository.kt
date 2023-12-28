package com.vendavo.micronaut

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.*
import jakarta.transaction.Transactional

@Repository
@Transactional
interface OrganizationRepository : CrudRepository<Organization, UUID>
