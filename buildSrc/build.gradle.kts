plugins {
    id("org.springframework.boot") apply false
    id("io.spring.dependency-management")
    id("com.gorylenko.gradle-git-properties")
    id("org.jlleitschuh.gradle.ktlint")

    `kotlin-dsl`
}

val gitPropertiesVersion: String by project
val springBootVersion: String by project
val testcontainersVersion: String by project

dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        mavenBom("org.testcontainers:testcontainers-bom:$testcontainersVersion")
    }
}

java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    maven(url = "https://plugins.gradle.org/m2/")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation("org.jetbrains.kotlin:kotlin-allopen")

    implementation("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
    implementation("com.gorylenko.gradle-git-properties:gradle-git-properties:$gitPropertiesVersion")

    implementation("org.jlleitschuh.gradle.ktlint:org.jlleitschuh.gradle.ktlint.gradle.plugin:11.3.1")
}
