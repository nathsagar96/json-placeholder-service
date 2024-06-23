# Album Service

The Album Service is a microservice designed to manage albums. It is built using Spring Boot and follows RESTful principles. This service allows for creating, retrieving, updating, and deleting albums, as well as retrieving albums by user ID.

## Table of Contents

- [Architecture](#architecture)
- [Endpoints](#endpoints)
- [Installation](#installation)
- [Usage](#usage)
- [Testing](#testing)

## Architecture

The Album Service is built with the following technologies:

- Spring Boot
- Spring Data JPA
- H2 Database (for in-memory database)
- Spring Boot DevTools (for development)

## Endpoints

### Album Endpoints

- `GET /albums`: Retrieve all albums
- `GET /albums/{id}`: Retrieve a album by ID
- `POST /albums`: Create a new album
- `PUT /albums/{id}`: Update a album by ID
- `DELETE /albums/{id}`: Delete a album by ID
- `GET /albums/user/{userId}`: Retrieve all albums by a specific user

## Installation

### Prerequisites

- JDK 17 or later
- Maven 3.8.0 or later

### Steps

1. Clone the repository:

    ```bash
    git clone https://github.com/nathsagar96/json-placeholder-service.git
    cd album-service
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

To facilitate testing, a Postman collection is provided. This collection includes requests to test all the endpoints of the Album Service.

1. Download the Postman collection file: [AlbumService.postman_collection.json](AlbumService.postman_collection.json)
2. Import the collection into Postman:
   - Open Postman.
   - Click on the "Import" button in the top left corner.
   - Select the "Upload Files" tab.
   - Choose the AlbumService.postman_collection.json file you downloaded.
   - Click the "Import" button.
3. Use the imported collection to test the various endpoints of the Album Service.

## Testing

### Running Unit Tests

Unit tests for the Album Service can be run with the following command:

```bash
mvn clean test
```

These tests will verify that the service behaves correctly and handles various scenarios.