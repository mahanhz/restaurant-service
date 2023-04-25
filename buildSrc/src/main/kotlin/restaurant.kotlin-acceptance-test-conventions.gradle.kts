import org.gradle.kotlin.dsl.*
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")

    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.allopen")
}

repositories {
    mavenCentral()
}

val versions = Versions.fromProperties(project.rootDir)

dependencies {
    testImplementation(project(":main-app"))

    testImplementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.cucumber:cucumber-junit:${versions["cucumberVersion"]}")
    testImplementation("io.cucumber:cucumber-java:${versions["cucumberVersion"]}")
    testImplementation("io.cucumber:cucumber-spring:${versions["cucumberVersion"]}")
    testImplementation("org.testcontainers:junit-jupiter:${versions["testcontainersVersion"]}")
    testImplementation("org.testcontainers:mysql:${versions["testcontainersVersion"]}")

    testImplementation("org.liquibase:liquibase-core:${versions["liquibaseVersion"]}")
}

tasks.named<BootJar>("bootJar") {
    enabled = false
}
