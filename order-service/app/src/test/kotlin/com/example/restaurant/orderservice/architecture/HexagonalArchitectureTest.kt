package com.example.restaurant.orderservice.architecture

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import com.example.restaurant.orderservice.OrderApplication


private const val PACKAGE_APPLICATION = "..application.."
private const val PACKAGE_DOMAIN = "..domain.."
private const val PACKAGE_INFRA = "..infra.."

@AnalyzeClasses(packagesOf = [OrderApplication::class])
class HexagonalArchitectureTest {

    @ArchTest
    val domain_should_not_depend_on_application: ArchRule = noClasses()
        .that()
        .resideInAPackage(PACKAGE_DOMAIN)
        .should()
        .dependOnClassesThat()
        .resideInAPackage(PACKAGE_APPLICATION)

    @ArchTest
    val domain_should_not_depend_on_configuration: ArchRule = noClasses()
        .that()
        .resideInAPackage(PACKAGE_DOMAIN)
        .should()
        .dependOnClassesThat()
        .resideInAPackage("..config..")

    @ArchTest
    val domain_should_not_depend_on_infrastructure: ArchRule = noClasses()
        .that()
        .resideInAPackage(PACKAGE_DOMAIN)
        .should()
        .dependOnClassesThat()
        .resideInAPackage(PACKAGE_INFRA)

    @ArchTest
    val domain_should_not_depend_on_spring: ArchRule = noClasses()
        .that()
        .resideInAPackage(PACKAGE_DOMAIN)
        .should()
        .dependOnClassesThat()
        .resideInAPackage("org.springframework..")
}