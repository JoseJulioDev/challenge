# Gateway API

## Description
The Gateway API is the main entry point for other APIs, routing requests between the **Authentication API** and the **Operations API**. It uses Spring Cloud for route management and load balancing.

## Technologies Used
- **Java 17**
- **Spring Boot 3.3.4**
- **Spring Cloud 2023.0.3**: For routing and load balancing management between APIs.
- **Maven**

## Default Port
The API starts on **port 8080** by default.

## Features
- **Request Routing**: Directs calls to the appropriate APIs:
  - **Authentication API** for login and registration endpoints (`/api/auth/**`).
  - **Operations API** for endpoints that perform mathematical operations and balance verification (`/api/operations/**`).
- **Load Balancing**: Distributes requests to active APIs.

## Main Endpoints
- **Authentication Endpoints**:
  - `POST /api/auth/register`: Routes to the Authentication API to register a new user.
  - `POST /api/auth/login`: Routes to the Authentication API to authenticate a user and return a JWT token.
  - `PUT /api/auth/status`: Routes to the Authentication API to update the user's status (active/inactive).

- **Operations Endpoints**:
  - `POST /api/operations`: Routes to the Operations API to perform a mathematical operation.
  - `GET /api/balance`: Routes to the Operations API to check a user's current balance.


## Installation
1. Clone the repository.
2. In the `gateway` directory, run:
   ```bash
   ./mvnw clean install

Start the API with:
   ./mvnw spring-boot:run
 ./mvnw spring-boot:run