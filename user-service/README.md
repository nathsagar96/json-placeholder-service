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

### Postman Collection

To facilitate testing, a Postman collection is provided. This collection includes requests to test all the endpoints of the User Service.

1. Download the Postman collection file: [UserService.postman_collection.json](UserService.postman_collection.json)
2. Import the collection into Postman:
   - Open Postman.
   - Click on the "Import" button in the top left corner.
   - Select the "Upload Files" tab.
   - Choose the UserService.postman_collection.json file you downloaded.
   - Click the "Import" button.
3. Use the imported collection to test the various endpoints of the User Service.

## Testing

### Running Unit Tests

Unit tests for the User Service can be run with the following command:

```bash
mvn clean test
```

These tests will verify that the service behaves correctly and handles various scenarios.