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

### Create a New Album

```bash
curl -X POST "http://localhost:8080/albums" -H "Content-Type: application/json" -d '{"userId":1, "name":"Summer Vacation 2023"}'
```

### Retrieve All Albums

```bash
curl -X GET "http://localhost:8080/albums"
```

### Retrieve a Album by ID

```bash
curl -X GET "http://localhost:8080/albums/1"
```

### Update a Album

```bash
curl -X PUT "http://localhost:8080/albums/1" -H "Content-Type: application/json" -d '{"userId": 1, "name": "Skiing Holiday"}'
```

### Delete a Album

```bash
curl -X DELETE "http://localhost:8080/albums/1"
```

### Retrieve Albums by user ID

```bash
curl -X GET "http://localhost:8080/albums/user/1"
```

## Testing

### Running Unit Tests

Unit tests for the Album Service can be run with the following command:

```bash
mvn clean test
```

These tests will verify that the service behaves correctly and handles various scenarios.