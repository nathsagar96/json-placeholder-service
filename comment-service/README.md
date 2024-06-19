# Comment Service

The Comment Service is a microservice designed to manage comments. It is built using Spring Boot and follows RESTful principles. This service allows for creating, retrieving, updating, and deleting comments, as well as retrieving comments by post ID.

## Table of Contents

- [Architecture](#architecture)
- [Endpoints](#endpoints)
- [Installation](#installation)
- [Usage](#usage)
- [Testing](#testing)

## Architecture

The Comment Service is built with the following technologies:

- Spring Boot
- Spring Data JPA
- H2 Database (for in-memory database)
- Spring Boot DevTools (for development)

## Endpoints

### Comment Endpoints

- `GET /comments`: Retrieve all comments
- `GET /comments/{id}`: Retrieve a comment by ID
- `POST /comments`: Create a new comment
- `PUT /comments/{id}`: Update a comment by ID
- `DELETE /comments/{id}`: Delete a comment by ID
- `GET /comments/post/{postId}`: Retrieve all comments by a specific post

## Installation

### Prerequisites

- JDK 17 or later
- Maven 3.8.0 or later

### Steps

1. Clone the repository:

    ```bash
    git clone https://github.com/nathsagar96/json-placeholder-service.git
    cd comment-service
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

### Create a New Comment

```bash
curl -X POST "http://localhost:8080/comments" -H "Content-Type: application/json" -d '{"postId": 1, "name": "John Doe", "email": "john.doe@example.com", "body": "This is a comment"}'
```

### Retrieve All Comments

```bash
curl -X GET "http://localhost:8080/comments"
```

### Retrieve a Comment by ID

```bash
curl -X GET "http://localhost:8080/comments/1"
```

### Update a Comment

```bash
curl -X PUT "http://localhost:8080/comments/1" -H "Content-Type: application/json" -d '{"postId": 1, "name": "John Doe", "email": "john.doe@example.com", "body": "This is an updated comment"
}'
```

### Delete a Comment

```bash
curl -X DELETE "http://localhost:8080/comments/1"
```

### Retrieve Comments by post ID

```bash
curl -X GET "http://localhost:8080/comments/post/1"
```

## Testing

### Running Unit Tests

Unit tests for the CommentService can be run with the following command:

```bash
mvn clean test
```

These tests will verify that the service behaves correctly and handles various scenarios.
