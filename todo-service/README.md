# Todo Service

The Todo Service is a microservice designed to manage todos. It is built using Spring Boot and follows RESTful
principles. This service allows for creating, retrieving, updating, and deleting todos, as well as retrieving todos by
user ID.

## Table of Contents

- [Architecture](#architecture)
- [Endpoints](#endpoints)
- [Installation](#installation)
- [Usage](#usage)
- [Testing](#testing)

## Architecture

The Todo Service is built with the following technologies:

- Spring Boot
- Spring Data JPA
- H2 Database (for in-memory database)
- Spring Boot DevTools (for development)

## Endpoints

### Todo Endpoints

- `GET /todos`: Retrieve all todos
- `GET /todos/{id}`: Retrieve a todo by ID
- `POST /todos`: Create a new todo
- `PUT /todos/{id}`: Update a todo by ID
- `DELETE /todos/{id}`: Delete a todo by ID
- `GET /todos/user/{userId}`: Retrieve all todos by a specific user

## Installation

### Prerequisites

- JDK 17 or later
- Maven 3.8.0 or later

### Steps

1. Clone the repository:

    ```bash
    git clone https://github.com/nathsagar96/json-placeholder-service.git
    cd todo-service
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

### Create a New Todo

```bash
curl -X POST "http://localhost:8080/todos" -H "Content-Type: application/json" -d '{"userId": 1,"title": "New Todo","completed": false}'
```

### Retrieve All Todos

```bash
curl -X GET "http://localhost:8080/todos"
```

### Retrieve a Todo by ID

```bash
curl -X GET "http://localhost:8080/todos/1"
```

### Update a Todo

```bash
curl -X PUT "http://localhost:8080/todos/1" -H "Content-Type: application/json" -d '{"userId": 1,"title": "Updated Todo","completed": true}'
```

### Delete a Todo

```bash
curl -X DELETE "http://localhost:8080/todos/1"
```

### Retrieve Todos by user ID

```bash
curl -X GET "http://localhost:8080/todos/user/1"
```

## Testing

### Running Unit Tests

Unit tests for the Todo Service can be run with the following command:

```bash
mvn clean test
```

These tests will verify that the service behaves correctly and handles various scenarios.