# Comment Service API

## Overview

This is a Spring Boot application that provides a RESTful API for managing blog comments. The API allows clients to
create, retrieve, update, and delete comments. Each comment contains a name, email, body, and is associated with a post.

## Prerequisites

- Java
- Maven
- Spring Boot
- Docker

## Getting Started

### Go to the project directory

```bash
cd comment-service
```

### Build the project

Use Maven to build the project:

```bash
mvn clean install
```

### Build the Docker image

If you prefer to run the service as a Docker container, build the Docker image using the provided `Dockerfile`:

```bash
docker build -t comment-service:latest .
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

The application will start and be accessible at `http://localhost:8081`.

#### Option 2: Run with Docker

Run the container using the built image:

```bash
docker run -d -p 8081:8081 comment-service:latest
```

The application will start and be accessible at `http://localhost:8081`.

## API Endpoints

### Get all comments

- URL: `/comments`
- Method: GET
- Response: A list of all comments

```json
[
  {
    "id": 1,
    "name": "Commenter 1",
    "email": "commenter1@example.com",
    "body": "This is a comment.",
    "postId": 1
  }
]
```

### Get a comment by ID

- URL: `/comments/{id}`
- Method: GET
- Path Variable: `id` (Integer) - ID of the comment
- Response: The comment with the specified ID

```json
{
  "id": 1,
  "name": "Commenter 1",
  "email": "commenter1@example.com",
  "body": "This is a comment.",
  "postId": 1
}
```

### Create a new comment

- URL: `/comments`
- Method: POST
- Request Body: A JSON object representing the new comment
- Response: The created comment

```json
{
  "id": 501,
  "name": "Commenter 3",
  "email": "commenter3@example.com",
  "body": "This is a new comment.",
  "postId": 1
}
```

### Update a comment

- URL: `/comments/{id}`
- Method: PUT
- Path Variable: `id` (Integer) - ID of the comment to update
- Request Body: A JSON object with the updated comment details
- Response: The updated comment

```json
{
  "id": 1,
  "name": "Updated Commenter",
  "email": "updated@example.com",
  "body": "This is an updated comment.",
  "postId": 1
}
```

### Delete a comment

- URL: `/comments/{id}`
- Method: DELETE
- Path Variable: `id` (Integer) - ID of the comment to delete
- Response: 204 No Content

### Get comments by post ID

- URL: `/comments/post/{postId}`
- Method: GET
- Path Variable: `postId` (Integer) - ID of the post
- Response: A list of comments by the specified post

```json
[
  {
    "id": 1,
    "name": "Commenter 1",
    "email": "commenter1@example.com",
    "body": "This is a comment.",
    "postId": 1
  }
]
```

## Exception Handling

**CommentNotFoundException**: Thrown when a comment with the specified ID is not found. Returns a 404 Not Found
response.

## Dependencies

- Spring Boot Starter Web: For building web applications, including RESTful services
- Spring Boot Starter Data JPA: For database access and manipulation
- H2 Database: In-memory database for testing

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.
