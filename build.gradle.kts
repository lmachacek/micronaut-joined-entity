plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.allopen)
    alias(libs.plugins.kotlin.jpa)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.micronaut.application)
    alias(libs.plugins.micronaut.aot)
    alias(libs.plugins.shadow)
}

version = "0.1"
group = "com.vendavo.micronaut"

repositories {
    mavenCentral()
}

dependencies {
    kaptTest("io.micronaut:micronaut-inject-java")

    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation(libs.kotlin.stdlib)

    compileOnly("io.micronaut:micronaut-http-client")

    runtimeOnly("ch.qos.logback:logback-classic")

    testImplementation("io.micronaut:micronaut-http-client")

    testRuntimeOnly("com.h2database:h2")
}

application {
    mainClass.set("com.vendavo.micronaut.ApplicationKt")
}

java {
    sourceCompatibility = JavaVersion.toVersion("17")
}

micronaut {
    version(libs.versions.micronaut.asProvider().get())
    runtime("netty")
    testRuntime("kotest")
    processing {
        incremental(true)
        annotations("com.vendavo.micronaut.*")
    }
}



