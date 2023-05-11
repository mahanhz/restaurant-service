import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	id("restaurant.kotlin-common-module-conventions")
	id("com.gorylenko.gradle-git-properties")
}

val versions = Versions.fromProperties(project.rootDir)

dependencies {
	implementation(project(":order-service:app"))
	implementation(project(":kitchen-service:app"))

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	runtimeOnly("io.opentelemetry.instrumentation:opentelemetry-logback-1.0:${versions["openTelemetryLogbackVersion"]}")

	implementation("org.liquibase:liquibase-core:${versions["liquibaseVersion"]}")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.tngtech.archunit:archunit-junit5:${versions["archUnitVersion"]}")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

springBoot {
	mainClass.set("com.example.restaurant.ApplicationKt")
	buildInfo()
}

tasks.named<BootJar>("bootJar") {
	archiveClassifier.set("boot")

	val artifactName: String by project
	archiveBaseName.set(artifactName)
}

tasks.named<Jar>("jar") {
	enabled = true
	archiveClassifier.set("")
}
