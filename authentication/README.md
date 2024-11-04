# Authentication API

## Description
The Authentication API manages user creation and authentication, allowing control over user status (active/inactive) and providing support for validations in other APIs. It uses **JWT (JSON Web Tokens)** to manage user sessions, ensuring security and authentication in subsequent requests.

## Technologies Used
- **Java 17**
- **Spring Boot 3.3.4**: For configuration and implementation of REST services.
- **JWT (JSON Web Token)**: For secure authentication and user session management.
- **JUnit**: For unit testing the API's functionalities.
- **Maven**: Build and dependency management.

## Default Port
The API starts on **port 8081** by default.

## Features
- **User Creation**: Creates new user accounts.
- **Authentication**: Verifies credentials and generates JWT session tokens.

## JWT Usage
After a successful login, the API generates a **JWT token** which is returned to the user. This token must be included in the header of all subsequent requests to access protected endpoints.

- **Token Generation**: Upon login, the API creates a JWT containing user identification information and an expiration time.
- **JWT Authorization**: In each request, the token is verified. If it is valid and has not expired, the user is authorized to access the requested resource.

## Main Endpoints
- `POST /api/auth/register: Registers a new user.
- `POST /api/auth/login: Authenticates a user, generates, and returns a JWT token.

### JWT Header Example
```http
Authorization: Bearer <jwt_token_here>

## Installation
1. Clone the repository.
2. In the `operations` directory, run:
   ```bash
   ./mvnw clean install

Start the API with:
   ./mvnw spring-boot:run

Unit Tests
The API includes unit tests implemented with JUnit to validate key functionalities, including:
    .User creation and authentication.
    .Active/inactive status verification.
    .JWT token validation.
    
To run the tests:
 ./mvnw test

