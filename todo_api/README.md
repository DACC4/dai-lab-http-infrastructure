# Todo API Documentation

## Overview
The Todo API is a Java-based RESTful web service for managing a list of tasks or "todos". It is built using Javalin, a lightweight Java framework for building web applications. The service allows for creating, retrieving, updating, and deleting todo items.

## Components

### TodoApi.java

**Package:** `ch.heigvd`

**Purpose:** This is the entry point for the API. It initializes the Javalin application and defines the routes for the RESTful services.

**Endpoints:**
- POST `/todos`: Create a new todo item.
- GET `/todos`: Retrieve all todo items.
- GET `/todos/{id}`: Retrieve a specific todo item by ID.
- PUT `/todos/{id}`: Update an existing todo item by ID.
- DELETE `/todos/{id}`: Delete a specific todo item by ID.

### TodoItem.java

**Package:** `ch.heigvd`

**Purpose:** Defines the structure of a todo item.

**Fields:**
- `id`: A unique identifier for the todo item (String).
- `task`: The description of the todo task (String).
- `completed`: A boolean indicating whether the task is completed.

**Methods:** Constructors, getters, and setters for the above fields.

### TodoManager.java

**Package:** `ch.heigvd`

**Purpose:** Manages the operations related to todo items, such as creating, updating, retrieving, and deleting todos.

**Details:**
- In-memory storage of todos is handled using a `ConcurrentHashMap` for thread safety.
- Methods correspond to the CRUD operations for todo items and manipulate the `ConcurrentHashMap` accordingly.

### Dockerfile

**Stages:**
- **Build Stage:** Uses `openjdk:21-slim` as the base image, installs Maven, and builds the application.
- **Run Stage:** Sets up the runtime environment, copies the built JAR from the build stage, and sets the command to run the application.

### docker-compose.yaml

**Version:** 3.8

**Service:**
- `todo_api`: Defines the build context and maps the container port 7070 to the host's port 7070.

## Usage

To use this application:
1. Build and run the service using Docker and Docker Compose. (`docker compose up`)
2. Interact with the API through the defined endpoints, typically using HTTP requests.

## Prerequisites

- Java Development Kit (JDK)
- Maven (for building the application)
- Docker and Docker Compose (for containerization and deployment)