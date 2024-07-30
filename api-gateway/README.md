# API Gateway

The API Gateway is a key component of our microservices architecture, acting as the single entry point for all client
interactions. It handles routing, authentication, rate limiting, and other cross-cutting concerns. This application uses
**Spring Cloud Gateway** for routing and **Spring Cloud Eureka Server** for service discovery.

## Features

- **Routing:** Routes client requests to appropriate microservices based on the request path.
- **Load Balancing:** Balances requests across multiple instances of a microservice.
- **Rate Limiting:** Limits the number of requests a client can make to prevent abuse.

## Getting Started

### Prerequisites

- **Java 21**
- **Maven**
- **Eureka Server:** Service Registry for service discovery.

### Go to the project directory

```sh
cd api-gateway
```

### Configuration

**Application Properties:** Configure the `application.yml` file with details about the services to route requests to.

```yaml
server:
  port: 8090

spring:
  cloud:
    gateway:
      routes:
        - id: user-service
          uri: lb://USER-SERVICE
          predicates:
            - Path=/users/**
        - id: post-service
          uri: lb://POST-SERVICE
          predicates:
            - Path=/posts/**
        # Add additional routes here

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```

### Build the project

Use Maven to build the project:

```sh
mvn clean install
```

### Run the application

Start the Spring Boot application:

```bash
mvn spring-boot:run
```

The API Gateway will be accessible at `http://localhost:8090`.

### Usage

- **Routing Requests:** The gateway routes incoming requests to the appropriate microservice based on the request path.
  For example, requests to `/users/**` are routed to the User Service.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.
