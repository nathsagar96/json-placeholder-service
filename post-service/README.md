# Post Service

The Post Service is a microservice designed to manage posts. It is built using Spring Boot and follows RESTful principles. This service allows for creating, retrieving, updating, and deleting posts, as well as retrieving posts by user ID.

## Table of Contents

- [Architecture](#architecture)
- [Endpoints](#endpoints)
- [Installation](#installation)
- [Usage](#usage)
- [Testing](#testing)

## Architecture

The Post Service is built with the following technologies:

- Spring Boot
- Spring Data JPA
- H2 Database (for in-memory database)
- Spring Boot DevTools (for development)

## Endpoints

### Post Endpoints

- `GET /posts`: Retrieve all posts
- `GET /posts/{id}`: Retrieve a post by ID
- `POST /posts`: Create a new post
- `PUT /posts/{id}`: Update a post by ID
- `DELETE /posts/{id}`: Delete a post by ID
- `GET /posts/user/{userId}`: Retrieve all posts by a specific user

## Installation

### Prerequisites

- JDK 17 or later
- Maven 3.8.0 or later

### Steps

1. Clone the repository:

    ```bash
    git clone https://github.com/nathsagar96/json-placeholder-service.git
    cd post-service
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

### Create a New Post

```bash
curl -X POST "http://localhost:8080/posts" -H "Content-Type: application/json" -d '{"userId":1, "title":"New Post", "body":"This is a new post"}'
```

### Retrieve All Posts

```bash
curl -X GET "http://localhost:8080/posts"
```

### Retrieve a Post by ID

```bash
curl -X GET "http://localhost:8080/posts/1"
```

### Update a Post

```bash
curl -X PUT "http://localhost:8080/posts/1" -H "Content-Type: application/json" -d '{"userId":1, "title":"Updated Post", "body":"This is an updated post"}'
```

### Delete a Post

```bash
curl -X DELETE "http://localhost:8080/posts/1"
```

### Retrieve Posts by User ID

```bash
curl -X GET "http://localhost:8080/posts/user/1"
```

## Testing

### Running Unit Tests

Unit tests for the Post Service can be run with the following command:

```bash
mvn clean install
```

These tests will verify that the service behaves correctly and handles various scenarios.
