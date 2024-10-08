# Post Service API

## Overview

This is a Spring Boot application that provides a RESTful API for managing blog posts. The API allows clients to create,
retrieve, update, and delete posts. Each post contains a title, body, and is associated with a user.

## Prerequisites

- Java
- Maven
- Spring Boot
- Docker

## Getting Started

### Go to the project directory

```bash
cd post-service
```

### Build the project

Use Maven to build the project:

```bash
mvn clean install
```

### Build the Docker image

If you prefer to run the service as a Docker container, build the Docker image using the provided `Dockerfile`:

```bash
docker build -t post-service:latest .
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

The application will start and be accessible at `http://localhost:8080`.

#### Option 2: Run with Docker

Run the container using the built image:

```bash
docker run -d -p 8080:8080 post-service:latest
```

The application will start and be accessible at `http://localhost:8080`.

## API Endpoints

### Get all posts

- URL: `/posts`
- Method: GET
- Response: A list of all posts

```json
[
  {
    "id": 1,
    "title": "Post Title",
    "body": "Post Body",
    "userId": 1
  }
]
```

### Get a post by ID

- URL: `/posts/{id}`
- Method: GET
- Path Variable: `id` (Integer) - ID of the post
- Response: The post with the specified ID

```json
{
  "id": 1,
  "title": "Post Title",
  "body": "Post Body",
  "userId": 1
}
```

### Create a new post

- URL: `/posts`
- Method: POST
- Request Body: A JSON object representing the new post
- Response: The created post

```json
{
  "id": 101,
  "title": "New Post Title",
  "body": "New Post Body",
  "userId": 1
}
```

### Update a post

- URL: `/posts/{id}`
- Method: PUT
- Path Variable: `id` (Integer) - ID of the post to update
- Request Body: A JSON object with the updated post details
- Response: The updated post

```json
{
  "id": 1,
  "title": "Updated Post Title",
  "body": "Updated Post Body",
  "userId": 1
}
```

### Delete a post

- URL: `/posts/{id}`
- Method: DELETE
- Path Variable: `id` (Integer) - ID of the post to delete
- Response: 204 No Content

### Get posts by user ID

- URL: `/posts/user/{userId}`
- Method: GET
- Path Variable: `userId` (Integer) - ID of the user
- Response: A list of posts by the specified user

```json
[
  {
    "id": 1,
    "title": "User Post Title",
    "body": "User Post Body",
    "userId": 1
  }
]
```

## Exception Handling

**PostNotFoundException**: Thrown when a post with the specified ID is not found. Returns a 404 Not Found response.

## Dependencies

- Spring Boot Starter Web: For building web applications, including RESTful services
- Spring Boot Starter Data JPA: For database access and manipulation
- H2 Database: In-memory database for testing

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.
