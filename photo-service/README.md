# Photo Service API

## Overview

This is a Spring Boot application that provides a RESTful API for managing album photos. The API allows clients to
create,
retrieve, update, and delete photos. Each photo contains a title, url, thumbnail url and is associated with an album.

## Prerequisites

- Java
- Maven
- Spring Boot

## Getting Started

### Go to the project directory

```bash
cd photo-service
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

The application will start and be accessible at http://localhost:8083.

## API Endpoints

### Get all photos

- URL: /api/v1/photos
- Method: GET
- Response: A list of all photos

```json
[
  {
    "id": 1,
    "title": "title",
    "url": "url",
    "thumbnailUrl": "thumbnailUrl",
    "albumId": 1
  }
]
```

### Get a photo by ID

- URL: /api/v1/photos/{id}
- Method: GET
- Path Variable: id (Integer) - ID of the photo
- Response: The photo with the specified ID

```json
{
  "id": 1,
  "title": "title",
  "url": "url",
  "thumbnailUrl": "thumbnailUrl",
  "albumId": 1
}
```

### Create a new photo

- URL: /api/v1/photos
- Method: POST
- Request Body: A JSON object representing the new photo
- Response: The created photo

```json
{
  "id": 5001,
  "title": "title",
  "url": "url",
  "thumbnailUrl": "thumbnailUrl",
  "albumId": 1
}
```

### Update a photo

- URL: /api/v1/photos/{id}
- Method: PUT
- Path Variable: id (Integer) - ID of the photo to update
- Request Body: A JSON object with the updated photo details
- Response: The updated photo

```json
{
  "id": 1,
  "title": "updated title",
  "url": "updated url",
  "thumbnailUrl": "updated thumbnailUrl",
  "albumId": 1
}
```

### Delete a photo

- URL: /api/v1/photos/{id}
- Method: DELETE
- Path Variable: id (Integer) - ID of the photo to delete
- Response: 204 No Content

### Get photos by post ID

- URL: /api/v1/photos/album/{albumId}
- Method: GET
- Path Variable: albumId (Integer) - ID of the album
- Response: A list of photos by the specified album

```json
[
  {
    "id": 1,
    "title": "title",
    "url": "url",
    "thumbnailUrl": "thumbnailUrl",
    "albumId": 1
  }
]
```

## Exception Handling

**PhotoNotFoundException**: Thrown when a photo with the specified ID is not found. Returns a 404 Not Found response.

## Dependencies

- Spring Boot Starter Web: For building web applications, including RESTful services
- Spring Boot Starter Data JPA: For database access and manipulation
- H2 Database: In-memory database for testing

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.
