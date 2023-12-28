package io.kotest.provided

import io.kotest.core.config.AbstractProjectConfig
import io.micronaut.test.extensions.kotest.MicronautKotestExtension

object ProjectConfig : AbstractProjectConfig() {
    override fun extensions() = listOf(MicronautKotestExtension)
    override fun listeners() = listOf(MicronautKotestExtension)
}
