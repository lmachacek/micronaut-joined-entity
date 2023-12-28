package com.vendavo.micronaut

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.*
import javax.transaction.Transactional

@Repository
@Transactional
interface TenantRepository : CrudRepository<Tenant, UUID>
