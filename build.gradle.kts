plugins {
    java
    id("org.springframework.boot") version "3.2.2" apply false
    id("io.spring.dependency-management") version "1.1.4" apply false
    id("org.liquibase.gradle") version "2.2.0" apply false
}

allprojects {
    group = "com.github.yildizmy"
    version = "1.0.0"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    dependencies {
        // Spring Cloud BOM
        implementation(platform("org.springframework.cloud:spring-cloud-dependencies:${rootProject.libs.versions.spring.cloud.get()}"))

        // Core dependencies for all services
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-devtools")

        // Zipkin Reporter for Brave
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("io.micrometer:micrometer-tracing-bridge-brave")
        implementation("io.zipkin.reporter2:zipkin-reporter-brave")

        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    // Shared dependencies for business services
    if (project.name !in listOf("config-server", "discovery-server", "common", "api-gateway")) {
        dependencies {
            implementation(project(":common"))
            implementation("org.springframework.boot:spring-boot-starter-web")
            implementation("org.springframework.boot:spring-boot-starter-data-jpa")
            implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
            implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")
            implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
            implementation("org.springframework.cloud:spring-cloud-starter-config")
            implementation("org.postgresql:postgresql:${rootProject.libs.versions.postgresql.get()}")
            implementation("org.liquibase:liquibase-core")
            implementation("com.hazelcast:hazelcast-spring:${rootProject.libs.versions.hazelcast.get()}")
            implementation("com.hazelcast:hazelcast:${rootProject.libs.versions.hazelcast.get()}")
            implementation("org.springframework.boot:spring-boot-starter-cache")

            // Testing
            testImplementation("org.testcontainers:postgresql:${rootProject.libs.versions.testcontainers.get()}")
            testImplementation("org.testcontainers:junit-jupiter:${rootProject.libs.versions.testcontainers.get()}")
        }
    }
}
