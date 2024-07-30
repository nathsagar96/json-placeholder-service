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

![architecure diagram](json-placeholder-service-architecture-diagram.png)

### Technologies Used

- Spring Boot
- Spring Data JPA
- H2 Database
- Spring Cloud

## Microservices

The JSON Placeholder Service is composed of the following microservices:

1. **Post Service**
   - Folder: [Post Service](post-service)
   - Manages posts.

2. **Comment Service**
   - Folder: [Comment Service](comment-service)
   - Manages comments.

3. **Album Service**
   - Folder: [Album Service](album-service)
   - Manages albums.

4. **Photo Service**
   - Folder: [Photo Service](photo-service)
   - Manages photos.

5. **Todo Service**
   - Folder: [Todo Service](todo-service)
   - Manages todos.

6. **User Service**
   - Folder: [User Service](user-service)
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

- `GET /posts`: Retrieve all posts
- `GET /posts/{id}`: Retrieve a post by ID
- `POST /posts`: Create a new post
- `PUT /posts/{id}`: Update a post by ID
- `DELETE /posts/{id}`: Delete a post by ID
- `GET /posts/user/{userId}`: Retrieve all posts by a specific user

### Comment Endpoints

- `GET /comments`: Retrieve all comments
- `GET /comments/{id}`: Retrieve a comment by ID
- `POST /comments`: Create a new comment
- `PUT /comments/{id}`: Update a comment by ID
- `DELETE /comments/{id}`: Delete a comment by ID
- `GET /comments/post/{postId}`: Retrieve all comments by a specific post

### Album Endpoints

- `GET /albums`: Retrieve all albums
- `GET /albums/{id}`: Retrieve an album by ID
- `POST /albums`: Create a new album
- `PUT /albums/{id}`: Update an album by ID
- `DELETE /albums/{id}`: Delete an album by ID
- `GET /albums/user/{userId}`: Retrieve all albums by a specific user

### Photo Endpoints

- `GET /photos`: Retrieve all photos
- `GET /photos/{id}`: Retrieve a photo by ID
- `POST /photos`: Create a new photo
- `PUT /photos/{id}`: Update a photo by ID
- `DELETE /photos/{id}`: Delete a photo by ID
- `GET /photos/album/{albumId}`: Retrieve all photos by a specific album

### Todo Endpoints

- `GET /todos`: Retrieve all todos
- `GET /todos/{id}`: Retrieve a todo by ID
- `POST /todos`: Create a new todo
- `PUT /todos/{id}`: Update a todo by ID
- `DELETE /todos/{id}`: Delete a todo by ID
- `GET /todos/user/{userId}`: Retrieve all todos by a specific user

### User Endpoints

- `GET /users`: Retrieve all users
- `GET /users/{id}`: Retrieve a user by ID
- `POST /users`: Create a new user
- `PUT /users/{id}`: Update a user by ID
- `DELETE /users/{id}`: Delete a user by ID

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

1. Fork the repository
2. Create a new branch (git checkout -b feature-branch)
3. Commit your changes (git commit -am 'Add new feature')
4. Push to the branch (git push origin feature-branch)
5. Create a new Pull Request
