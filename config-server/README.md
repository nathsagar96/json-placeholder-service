# Config Server

This is the Config Server for our microservices architecture. The Config Server provides centralized configuration
management for all microservices, ensuring consistency and ease of management. It fetches configuration properties from
an external Git repository, allowing dynamic updates to configurations without redeploying the services.

## Features

- **Centralized Configuration Management:** Manages configuration properties for all microservices.
- **Dynamic Property Updates:** Updates configurations dynamically from a Git repository without redeploying services.
- **Environment-Specific Configurations:** Supports multiple profiles for different environments (
  e.g., `development`, `staging`, `production`).
- **Security:** Integrates with secure Git repositories and supports encrypted properties.

## Getting Started

### Prerequisites

- **JDK 21**
- **Maven**
- **Git Repository for Configuration Files**

### Go to the project directory

```sh
cd config-server
```

### Configuration

1. **Application Properties:** Configure the `application.yml` with the URL of your Git repository and any necessary
   credentials.

```yaml
server:
  port: 8888

spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/your-repo/config-repo
          username: your-username
          password: your-password
```

2. **Profiles:** Ensure your Git repository contains configuration files for each environment (
   e.g., `application-dev.yml`, `application-prod.yml`).

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

The application will start and be accessible at http://localhost:8888.

### Accessing Configurations

Microservices can access their configuration properties by using the following URL pattern:

```
http://<config-server-host>:<port>/<application-name>/<profile>
```

For example, to access the configurations for the `user-service` in the `development` environment:

```
http://localhost:8888/user-service/dev
```

## Security

To secure sensitive configuration properties:

1. **Encrypting Properties:** Use the Spring Cloud Config Server's built-in encryption support. Ensure you set up a
   symmetric or asymmetric key for encryption.

2. **Secure Git Repository:** Use secure credentials and access control for your Git repository to protect configuration
   files.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.
