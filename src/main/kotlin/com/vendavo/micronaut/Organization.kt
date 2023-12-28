package com.vendavo.micronaut

import java.util.*
import jakarta.persistence.*

@Entity
@Table(name = "organizations")
data class Organization(
    @Id
    val id: UUID,

    @Column(name = "name")
    var name: String,
) {

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id")
    var tenants: Set<Tenant> = mutableSetOf()
}
