rootProject.name = "ecommerce"

include(":api-gateway")
include(":config-server")
include(":discovery-server")
include(":common")
include(":inventory-service")
include(":notification-service")
include(":order-service")
include(":payment-service")
include(":product-service")
include(":user-service")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("spring-boot", "3.2.2")
            version("spring-cloud", "2023.0.0")
            version("postgresql", "42.7.1")
            version("testcontainers", "1.19.3")
            version("hazelcast", "5.3.6")
        }
    }
}
