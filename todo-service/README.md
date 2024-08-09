# Todo Service API

## Overview

This is a Spring Boot application that provides a RESTful API for managing todos. The API allows clients to
create,
retrieve, update, and delete todos. Each todo contains a title, status and is associated with a user.

## Prerequisites

- Java
- Maven
- Spring Boot

## Getting Started

### Go to the project directory

```bash
cd todo-service
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

The application will start and be accessible at http://localhost:8084.

## API Endpoints

### Get all todos

- URL: /todos
- Method: GET
- Response: A list of all todos

```json
[
  {
    "id": 1,
    "title": "title",
    "completed": false,
    "userId": 1
  }
]
```

### Get a todo by ID

- URL: /todos/{id}
- Method: GET
- Path Variable: id (Integer) - ID of the todo
- Response: The todo with the specified ID

```json
{
  "id": 1,
  "title": "title",
  "completed": false,
  "userId": 1
}
```

### Create a new todo

- URL: /todos
- Method: POST
- Request Body: A JSON object representing the new todo
- Response: The created todo

```json
{
  "id": 201,
  "title": "title",
  "completed": false,
  "userId": 1
}
```

### Update a todo

- URL: /todos/{id}
- Method: PUT
- Path Variable: id (Integer) - ID of the todo to update
- Request Body: A JSON object with the updated todo details
- Response: The updated todo

```json
{
  "id": 1,
  "title": "updated title",
  "completed": false,
  "userId": 1
}
```

### Delete a todo

- URL: /todos/{id}
- Method: DELETE
- Path Variable: id (Integer) - ID of the todo to delete
- Response: 204 No Content

### Get todos by user ID

- URL: /todos/user/{userId}
- Method: GET
- Path Variable: userId (Integer) - ID of the user
- Response: A list of todos by the specified user

```json
[
  {
    "id": 1,
    "title": "title",
    "completed": false,
    "userId": 1
  }
]
```

## Exception Handling

**TodoNotFoundException**: Thrown when a todo with the specified ID is not found. Returns a 404 Not Found response.

## Dependencies

- Spring Boot Starter Web: For building web applications, including RESTful services
- Spring Boot Starter Data JPA: For database access and manipulation
- H2 Database: In-memory database for testing

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.
