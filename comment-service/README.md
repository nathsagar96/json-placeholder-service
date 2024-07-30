# Comment Service API

## Overview

This is a Spring Boot application that provides a RESTful API for managing blog comments. The API allows clients to
create,
retrieve, update, and delete comments. Each comment contains a name, email, body, and is associated with a post.

## Prerequisites

- Java
- Maven
- Spring Boot

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

### Run the application

Start the Spring Boot application:

```bash
mvn spring-boot:run
```

The application will start and be accessible at http://localhost:8081.

## API Endpoints

### Get all comments

- URL: /api/v1/comments
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

- URL: /api/v1/comments/{id}
- Method: GET
- Path Variable: id (Integer) - ID of the comment
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

- URL: /api/v1/comments
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

- URL: /api/v1/comments/{id}
- Method: PUT
- Path Variable: id (Integer) - ID of the comment to update
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

- URL: /api/v1/comments/{id}
- Method: DELETE
- Path Variable: id (Integer) - ID of the comment to delete
- Response: 204 No Content

### Get comments by post ID

- URL: /api/v1/comments/post/{postId}
- Method: GET
- Path Variable: postId (Integer) - ID of the post
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

**CommentNotFoundException**: Thrown when a comment with the specified ID is not found. Returns a 404 Not Found response.

## Dependencies

- Spring Boot Starter Web: For building web applications, including RESTful services
- Spring Boot Starter Data JPA: For database access and manipulation
- H2 Database: In-memory database for testing

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.
