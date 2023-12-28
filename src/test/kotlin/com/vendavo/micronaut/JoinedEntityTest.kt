package com.vendavo.micronaut

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.optional.shouldBePresent
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import java.util.*

@MicronautTest(startApplication = false)
class JoinedEntityTest(
    private val organizationRepository: OrganizationRepository,
    private val tenantRepository: TenantRepository,
) : FunSpec({

    test("Joined entity is loaded") {
        val organization = Organization(
            id = UUID.randomUUID(),
            name = "Some Organization",
        )
        organizationRepository.save(organization)
        val tenant = Tenant(
            id = UUID.randomUUID(),
            organizationId = organization.id,
            alias = "tenant-1",
        )
        tenantRepository.save(tenant)

        val loadedOrganization = organizationRepository.findById(organization.id)
        loadedOrganization.shouldBePresent()
        loadedOrganization.get().tenants shouldContain tenant
    }
})
