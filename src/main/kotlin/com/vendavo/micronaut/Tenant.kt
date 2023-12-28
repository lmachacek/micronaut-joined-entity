package com.vendavo.micronaut

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tenants")
data class Tenant(
    @Id
    val id: UUID,

    @Column(name = "organization_id")
    var organizationId: UUID,

    @Column(name = "alias")
    var alias: String,
)
