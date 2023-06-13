pluginManagement {
    val gitPropertiesVersion: String by settings
    val ktlintVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings

    plugins {
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version springDependencyManagementVersion
        id("com.gorylenko.gradle-git-properties") version gitPropertiesVersion
        id("org.jlleitschuh.gradle.ktlint") version ktlintVersion
    }

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}
