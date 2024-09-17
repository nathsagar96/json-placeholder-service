# Photo Service API

## Overview

This is a Spring Boot application that provides a RESTful API for managing album photos. The API allows clients to
create, retrieve, update, and delete photos. Each photo contains a title, URL, thumbnail URL, and is associated with an
album.

## Prerequisites

- Java
- Maven
- Spring Boot
- Docker

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

### Build the Docker image

If you prefer to run the service as a Docker container, build the Docker image using the provided `Dockerfile`:

```bash
docker build -t photo-service:latest .
```

### Run the application

Ensure the following services are running before starting the application:

- Config Service
- Discovery Service
- Loki
- Prometheus
- Grafana
- Tempo

If these services are not running, start them using the docker-compose.yml file in the root directory.

You can run the application either locally using Maven or as a Docker container.

#### Option 1: Run with Maven

Start the Spring Boot application:

```bash
mvn spring-boot:run
```

The application will start and be accessible at `http://localhost:8083`.

#### Option 2: Run with Docker

Run the container using the built image:

```bash
docker run -d -p 8083:8083 photo-service:latest
```

The application will start and be accessible at `http://localhost:8083`.

## API Endpoints

### Get all photos

- URL: `/photos`
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

- URL: `/photos/{id}`
- Method: GET
- Path Variable: `id` (Integer) - ID of the photo
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

- URL: `/photos`
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

- URL: `/photos/{id}`
- Method: PUT
- Path Variable: `id` (Integer) - ID of the photo to update
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

- URL: `/photos/{id}`
- Method: DELETE
- Path Variable: `id` (Integer) - ID of the photo to delete
- Response: 204 No Content

### Get photos by album ID

- URL: `/photos/album/{albumId}`
- Method: GET
- Path Variable: `albumId` (Integer) - ID of the album
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
