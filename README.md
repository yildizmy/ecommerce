## e-commerce Platform [DRAFT: Work in Progress]

A scalable, event-driven e-commerce platform built with Spring Boot microservices architecture. This platform provides a robust foundation for building and operating an enterprise-grade online retail system with high availability and fault tolerance.

## System Architecture

The platform consists of the following microservices:

- **API Gateway**: Single entry point that handles all client requests, providing routing, security, and load balancing
- **Config Server**: Centralized configuration management for all microservices
- **Discovery Server**: Service registry and discovery using Netflix Eureka
- **Common**: Shared libraries, utilities, and domain models used across services
- **Product Service**: Manages product catalog, categories, and inventory
- **Inventory Service**: Handles stock management and availability
- **Order Service**: Processes customer orders and manages order lifecycle
- **Payment Service**: Handles payment processing and transaction management
- **Notification Service**: Manages customer communications and alerts
- **User Service**: Handles user authentication, authorization, and profile management

## Tech Stack

- Java 21
- Spring Boot
- Apache Kafka (Event-driven communication)
- Spring Cloud Netflix (Service Discovery)
- Spring Cloud Config (Configuration Management)
- Spring Cloud Gateway
- Spring Cloud OpenFeign (Declarative REST Client)
- Zipkin (Distributed Tracing)
- Liquibase (Database Migrations)
- PostgreSQL
- Docker

## Key Features

- Event-driven architecture using Apache Kafka
- Circuit breaking and fault tolerance
- Distributed tracing
- Centralized logging
- Secure by design with OAuth2/JWT authentication
- Horizontal scalability
- Real-time inventory updates
- Asynchronous communication between services
