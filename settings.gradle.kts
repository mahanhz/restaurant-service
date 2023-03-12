pluginManagement {
    repositories {
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }
}

rootProject.name = "restaurant-service"

include("main-app")
include("order-service:app", "order-service:tests-acceptance")
include("kitchen-service:app", "kitchen-service:tests-acceptance")
include("common-events")
