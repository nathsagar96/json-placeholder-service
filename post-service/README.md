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

### Postman Collection

To facilitate testing, a Postman collection is provided. This collection includes requests to test all the endpoints of the Post Service.

1. Download the Postman collection file: [PostService.postman_collection.json](PostService.postman_collection.json)
2. Import the collection into Postman:
   - Open Postman.
   - Click on the "Import" button in the top left corner.
   - Select the "Upload Files" tab.
   - Choose the PostService.postman_collection.json file you downloaded.
   - Click the "Import" button.
3. Use the imported collection to test the various endpoints of the Post Service.

## Testing

### Running Unit Tests

Unit tests for the Post Service can be run with the following command:

```bash
mvn clean test
```

These tests will verify that the service behaves correctly and handles various scenarios.
