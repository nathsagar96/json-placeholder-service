# Photo Service

The Photo Service is a microservice designed to manage photos. It is built using Spring Boot and follows RESTful principles. This service allows for creating, retrieving, updating, and deleting photos, as well as retrieving photos by album ID.

## Table of Contents

- [Architecture](#architecture)
- [Endpoints](#endpoints)
- [Installation](#installation)
- [Usage](#usage)
- [Testing](#testing)

## Architecture

The Photo Service is built with the following technologies:

- Spring Boot
- Spring Data JPA
- H2 Database (for in-memory database)
- Spring Boot DevTools (for development)

## Endpoints

### Photo Endpoints

- `GET /photos`: Retrieve all photos
- `GET /photos/{id}`: Retrieve a photo by ID
- `POST /photos`: Create a new photo
- `PUT /photos/{id}`: Update a photo by ID
- `DELETE /photos/{id}`: Delete a photo by ID
- `GET /photos/album/{albumId}`: Retrieve all photos from a specific album

## Installation

### Prerequisites

- JDK 17 or later
- Maven 3.8.0 or later

### Steps

1. Clone the repository:

    ```bash
    git clone https://github.com/nathsagar96/json-placeholder-service.git
    cd photo-service
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

To facilitate testing, a Postman collection is provided. This collection includes requests to test all the endpoints of the Photo Service.

1. Download the Postman collection file: [PhotoService.postman_collection.json](PhotoService.postman_collection.json)
2. Import the collection into Postman:
   - Open Postman.
   - Click on the "Import" button in the top left corner.
   - Select the "Upload Files" tab.
   - Choose the PhotoService.postman_collection.json file you downloaded.
   - Click the "Import" button.
3. Use the imported collection to test the various endpoints of the Photo Service.

## Testing

### Running Unit Tests

Unit tests for the Photo Service can be run with the following command:

```bash
mvn clean test
```

These tests will verify that the service behaves correctly and handles various scenarios.