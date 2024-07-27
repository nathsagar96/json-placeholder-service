# JSON Placeholder Service

The JSON Placeholder Service is a group of Spring Boot applications designed to mimic the functionalities of the [JSONPlaceholder](https://jsonplaceholder.typicode.com/) service. This service provides RESTful apis for handling users, posts, comments, and albums, demonstrating a microservices architecture using Spring Cloud.

## Table of Contents

- [Architecture](#architecture)
- [Microservices](#microservices)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)

## Architecture

This project follows a microservices architecture, where each functionality is separated into individual services. The project leverages Spring Cloud for api gateway, service discovery and configuration management.

### Technologies Used

- Spring Boot
- Spring Data JPA
- H2 Database
- Spring Cloud

## Microservices

The JSON Placeholder Service is composed of the following microservices:

1. **Post Service**
   - Repository: [Post Service Repository](post-service)
   - Manages posts.

2. **Comment Service**
   - Repository: [Comment Service Repository](comment-service)
   - Manages comments.

3. **Album Service**
   - Repository: [Album Service Repository](album-service)
   - Manages albums.

4. **Photo Service**
   - Repository: [Photo Service Repository](photo-service)
   - Manages photos.

5. **Todo Service**
   - Repository: [Todo Service Repository](todo-service)
   - Manages todos.

6. **User Service**
   - Repository: [User Service Repository](user-service)
   - Manages users.

## Installation

### Prerequisites

- JDK 21
- Maven
- Spring Boot

### Steps

1. Clone the repository:

    ```bash
    git clone https://github.com/nathsagar96/json-placeholder-service.git
    cd json-placeholder-service
    ```

2. Follow instructions given in the readme file for individual applications.

## Usage

### Post Endpoints

- `GET /api/v1/posts`: Retrieve all posts
- `GET /api/v1/posts/{id}`: Retrieve a post by ID
- `POST /api/v1/posts`: Create a new post
- `PUT /api/v1/posts/{id}`: Update a post by ID
- `DELETE /api/v1/posts/{id}`: Delete a post by ID
- `GET /api/v1/posts/user/{userId}`: Retrieve all posts by a specific user

### Comment Endpoints

- `GET /api/v1/comments`: Retrieve all comments
- `GET /api/v1/comments/{id}`: Retrieve a comment by ID
- `POST /api/v1/comments`: Create a new comment
- `PUT /api/v1/comments/{id}`: Update a comment by ID
- `DELETE /api/v1/comments/{id}`: Delete a comment by ID
- `GET /api/v1/comments/post/{postId}`: Retrieve all comments by a specific post

### Album Endpoints

- `GET /api/v1/albums`: Retrieve all albums
- `GET /api/v1/albums/{id}`: Retrieve an album by ID
- `POST /api/v1/albums`: Create a new album
- `PUT /api/v1/albums/{id}`: Update an album by ID
- `DELETE /api/v1/albums/{id}`: Delete an album by ID
- `GET /api/v1/albums/user/{userId}`: Retrieve all albums by a specific user

### Photo Endpoints

- `GET /api/v1/photos`: Retrieve all photos
- `GET /api/v1/photos/{id}`: Retrieve a photo by ID
- `POST /api/v1/photos`: Create a new photo
- `PUT /api/v1/photos/{id}`: Update a photo by ID
- `DELETE /api/v1/photos/{id}`: Delete a photo by ID
- `GET /api/v1/photos/album/{albumId}`: Retrieve all photos by a specific album

### Todo Endpoints

- `GET /api/v1/todos`: Retrieve all todos
- `GET /api/v1/todos/{id}`: Retrieve a todo by ID
- `POST /api/v1/todos`: Create a new todo
- `PUT /api/v1/todos/{id}`: Update a todo by ID
- `DELETE /api/v1/todos/{id}`: Delete a todo by ID
- `GET /api/v1/todos/user/{userId}`: Retrieve all todos by a specific user

### User Endpoints

- `GET /api/v1/users`: Retrieve all users
- `GET /api/v1/users/{id}`: Retrieve a user by ID
- `POST /api/v1/users`: Create a new user
- `PUT /api/v1/users/{id}`: Update a user by ID
- `DELETE /api/v1/users/{id}`: Delete a user by ID

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

1. Fork the repository
2. Create a new branch (git checkout -b feature-branch)
3. Commit your changes (git commit -am 'Add new feature')
4. Push to the branch (git push origin feature-branch)
5. Create a new Pull Request
