import org.gradle.api.tasks.testing.Test
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("org.jlleitschuh.gradle.ktlint")

    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.allopen")

    java
    jacoco
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    coloredOutput.set(true)
    reporters {
        reporter(ReporterType.CHECKSTYLE)
        reporter(ReporterType.JSON)
        reporter(ReporterType.HTML)
    }
    filter {
        exclude("**/style-violations.kts")
    }
}

val versions = Versions.fromProperties(project.rootDir)

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:${versions["jacksonVersion"]}")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// So that gradle understands module-info.java
java {
    modularity.inferModulePath.set(true)
}
val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
val compileJava: JavaCompile by tasks
compileKotlin.destinationDirectory.set(compileJava.destinationDirectory)
