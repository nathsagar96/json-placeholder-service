# Service Registry (Eureka Server)

The Service Registry is a crucial component in our microservices architecture. It serves as a discovery server where microservices register themselves at runtime. This allows other microservices and the API Gateway to discover and communicate with each other dynamically, providing a highly scalable and resilient system.

## Features

- **Dynamic Service Registration and Discovery:** Microservices register with the Eureka Server at startup and deregister on shutdown.
- **Client-Side Load Balancing:** Enables load balancing across instances of a service.
- **Health Checks:** Monitors the health status of registered microservices.
- **Instance Metadata:** Stores metadata about service instances, such as instance ID, IP address, port, and other custom attributes.

## Getting Started

### Prerequisites

- **JDK 21**
- **Maven**
- **Docker** (Optional, for containerization)

### Go to the project directory

```sh
cd service-registry
```

### Configuration

1. **Application Properties:** The default configuration is set up in the `application.yml` file. You can modify it to suit your environment.

```yaml
server:
  port: 8761

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```

2. **Peer Awareness (Optional):** For a highly available setup, configure peer awareness among multiple Eureka servers.

### Build the project

Use Maven to build the project:

```sh
mvn clean install
```

### Build the Docker image (Optional)

If you prefer to run the service as a Docker container, build the Docker image using the provided `Dockerfile`:

```sh
docker build -t service-registry:latest .
```

### Run the application

You can run the application either locally using Maven or as a Docker container.

#### Option 1: Run with Maven

Start the Spring Boot application:

```bash
mvn spring-boot:run
```

The Eureka Dashboard will be available at `http://localhost:8761`.

#### Option 2: Run with Docker

Run the container using the built image:

```bash
docker run -d -p 8761:8761 service-registry:latest
```

The Eureka Dashboard will be available at `http://localhost:8761`.

### Registering Microservices

Microservices can register with the Eureka Server by including the following dependencies and configurations:

**Dependencies:**

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

**Application Properties for Microservices:**

```yaml
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```

## Monitoring and Health Checks

The Eureka Dashboard provides a web interface to view registered services, their statuses, and instance information. The dashboard also allows monitoring the health of each registered service.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.
