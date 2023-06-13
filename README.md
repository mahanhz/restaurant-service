This project is connected to the article ["A path to production"](https://mahanhz.medium.com/a-path-to-production-4928a71e5de7)

# Contents

- Event Storming
- Example Mapping
- C4 modelling
- Domain Driven Design
- Gradle multi module project (using buildSrc)
- Kotlin + Spring boot + JPA
- Hexagonal architecture
- Enforcing architectural boundaries (Java Modules aka JPMS, Archunit)
- Behaviour Driven Development (Cucumber)
- Observability (via Open Telemetry)

# Generate modelling diagram

## Linux
**java -jar plantuml-1.2023.2.jar order-service/design/modelling/order.txt**

## Windows
**java -jar plantuml-1.2023.2.jar .\order-service\design\modelling\order.txt**

# Running tests and building artifact

The integration tests use TestContainers so docker is needed. See https://www.testcontainers.org/#prerequisites  

## Linux 
* Run unit and integration tests (and builds the artifact): **./gradlew clean build**
* Run acceptance tests: **./gradlew acceptanceTest**

## Windows
* Run unit and integration tests (and builds the artifact): **.\gradlew.bat clean build**
* Run acceptance tests: **.\gradlew.bat acceptanceTest**

## Code formatting
**ktlint** (https://pinterest.github.io/ktlint) via https://github.com/jlleitschuh/ktlint-gradle  
* Format script files: **./gradlew runKtlintFormatOverKotlinScripts**
* Format source files: **./gradlew ktlintFormat**

# Set up the infrastructure (via docker-compose)

## Linux
docker-compose -f docker/mysql.yml up -d  
docker-compose -f docker/open-telemetry.yml up -d  

## Windows
docker compose -f .\docker\mysql.yml up -d  
docker compose -f .\docker\open-telemetry.yml up -d


**Note:** If you modify the setup script of any dockerfile, then delete the docker container and rebuild using --build to pick up the changes.

# Run the application

## Vanilla
Linux: **java -Dspring.profiles.active=local -jar main-app/build/libs/restaurant-service-boot.jar** or **SPRING_PROFILES_ACTIVE=local ./gradlew bootRun**   

Windows: **java "-Dspring.profiles.active=local" -jar .\main-app\build\libs\restaurant-service-boot.jar** or **.\gradlew.bat bootRun --args='--spring.profiles.active=local'**  

## Run with export to open telemetry collector
Linux: **OTEL_SERVICE_NAME=restaurant-service OTEL_EXPORTER_OTLP_ENDPOINT="http://localhost:4317" java -javaagent:./opentelemetry-javaagent.jar -Dspring.profiles.active=local -jar main-app/build/libs/restaurant-service-boot.jar**  

Windows:  **java -javaagent:.\opentelemetry-javaagent.jar "-Dspring.profiles.active=local" "-DOTEL_SERVICE_NAME=restaurant-service" "-DOTEL_EXPORTER_OTLP_ENDPOINT=http://localhost:4317" -jar .\main-app\build\libs\restaurant-service-boot.jar**

# REST commands

**Swagger UI:** http://localhost:8011/swagger-ui.html

## Create a chef
POST http://localhost:8011/chefs
```json
{
  "chef": "Ratata"
}
```

## Get a chef
GET http://localhost:8011/chef/1

## Create an order
POST http://localhost:8011/orders
```json
{
    "date": "2023-04-01",
    "items": [
        {
            "name": "Burger",
            "quantity": 1
        }
    ]
}
```

## Get an order
GET http://localhost:8011/orders/1

# Observability

## Jaeger (tracing)
http://localhost:16686/

## Prometheus (metrics)
http://localhost:9090/