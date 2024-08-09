# Album Service API

## Overview

This is a Spring Boot application that provides a RESTful API for managing albums. The API allows clients to
create,
retrieve, update, and delete albums. Each album contains a title and is associated with a user.

## Prerequisites

- Java
- Maven
- Spring Boot

## Getting Started

### Go to the project directory

```bash
cd album-service
```

### Build the project

Use Maven to build the project:

```bash
mvn clean install
```

### Run the application

Start the Spring Boot application:

```bash
mvn spring-boot:run
```

The application will start and be accessible at http://localhost:8082.

## API Endpoints

### Get all albums

- URL: /albums
- Method: GET
- Response: A list of all albums

```json
[
  {
    "id": 1,
    "title": "Title",
    "userId": 1
  }
]
```

### Get an album by ID

- URL: /albums/{id}
- Method: GET
- Path Variable: id (Integer) - ID of the album
- Response: The album with the specified ID

```json
{
  "id": 1,
  "title": "Title",
  "userId": 1
}
```

### Create a new album

- URL: /albums
- Method: POST
- Request Body: A JSON object representing the new album
- Response: The created album

```json
{
  "id": 101,
  "title": "Title",
  "userId": 1
}
```

### Update a album

- URL: /albums/{id}
- Method: PUT
- Path Variable: id (Integer) - ID of the album to update
- Request Body: A JSON object with the updated album details
- Response: The updated album

```json
{
  "id": 1,
  "title": "Updated Title",
  "userId": 1
}
```

### Delete a album

- URL: /albums/{id}
- Method: DELETE
- Path Variable: id (Integer) - ID of the album to delete
- Response: 204 No Content

### Get albums by user ID

- URL: /albums/user/{userId}
- Method: GET
- Path Variable: userId (Integer) - ID of the user
- Response: A list of albums by the specified user

```json
[
  {
    "id": 1,
    "title": "Title",
    "userId": 1
  }
]
```

## Exception Handling

**AlbumNotFoundException**: Thrown when an album with the specified ID is not found. Returns a 404 Not Found response.

## Dependencies

- Spring Boot Starter Web: For building web applications, including RESTful services
- Spring Boot Starter Data JPA: For database access and manipulation
- H2 Database: In-memory database for testing

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.
