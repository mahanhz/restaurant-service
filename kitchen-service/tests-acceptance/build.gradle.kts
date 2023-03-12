plugins {
    id("restaurant.kotlin-acceptance-test-conventions")
}

dependencies {
    implementation(project(":kitchen-service:app"))
    implementation(project(":order-service:app"))
}

configurations {}

val cucumberRuntime: Configuration by configurations.creating {
    extendsFrom(configurations["testImplementation"])
}

tasks.register<Test>("acceptanceTest") {
    dependsOn("assemble", "compileTestJava")
    doLast {
        javaexec {
            mainClass.set("io.cucumber.core.cli.Main")
            classpath = cucumberRuntime + sourceSets.main.get().output + sourceSets.test.get().output
            args = listOf("--plugin", "pretty", "--plugin", "html:build/reports/cucumber.html", "--plugin", "json:build/reports/cucumber.json", "--plugin", "junit:build/test-results/acceptanceTest/cucumber-junit-report.xml", "--glue", "com.example.restaurant.kitchenservice.steps", "src/test/resources/features")
        }
    }
}
