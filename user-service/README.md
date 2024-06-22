# User Service

The User Service is a microservice designed to manage users. It is built using Spring Boot and follows RESTful principles. This service allows for creating, retrieving, updating, and deleting users.

## Table of Contents

- [Architecture](#architecture)
- [Endpoints](#endpoints)
- [Installation](#installation)
- [Usage](#usage)
- [Testing](#testing)

## Architecture

The User Service leverages the following technologies:

- Spring Boot
- Spring Data JPA
- H2 Database (for in-memory database)
- Spring Boot DevTools (for development)

## Endpoints

### User Endpoints

- `GET /users`: Retrieve all users.
- `GET /users/{id}`: Retrieve a user by ID.
- `POST /users`: Create a new user.
- `PUT /users/{id}`: Update a user by ID.
- `DELETE /users/{id}`: Delete a user by ID.

## Installation

### Prerequisites

- JDK 17 or later
- Maven 3.8.0 or later

### Steps

1. Clone the repository:

    ```bash
    git clone https://github.com/nathsagar96/json-placeholder-service.git
    cd user-service
    ```

2. Build the project using Maven:

    ```bash
    mvn clean install
    ```

3. Run the application:

    ```bash
    mvn spring-boot:run
    ```

The application will start and run on `http://localhost:8080`.

## Usage

### Create a New User

```bash
curl -X POST "http://localhost:8080/users" -H "Content-Type: application/json" -d '{
    "name": "John Doe",
    "username": "johndoe",
    "email": "johndoe@example.com"
}'
```

### Retrieve All Users

```bash
curl -X GET "http://localhost:8080/users"
```

### Retrieve a User by ID

```bash
curl -X GET "http://localhost:8080/users/1"
```

### Update a User

```bash
curl -X PUT "http://localhost:8080/users/1" -H "Content-Type: application/json" -d '{
    "name": "John Doe Updated",
    "username": "johndoe_updated",
    "email": "johndoe_updated@example.com"
}'
```

### Delete a User

```bash
curl -X DELETE "http://localhost:8080/users/1"
```

## Testing

### Running Unit Tests

Unit tests for the User Service can be run with the following command:

```bash
mvn clean test
```

These tests will verify that the service behaves correctly and handles various scenarios.