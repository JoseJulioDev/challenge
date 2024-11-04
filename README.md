# Microservices System

## Overview
This system consists of three interconnected microservices:
1. **Authentication API**: Manages user authentication and status.
2. **Operations API**: Performs mathematical operations and verifies user balance.
3. **Gateway API**: Serves as the central entry point, routing requests to the appropriate APIs.

## Architecture
The system is built using a microservices architecture with centralized routing handled by the **Gateway API**, configured to run on **port 8080**. All external requests enter through the Gateway, which automatically redirects them to the responsible API based on the requested endpoint:
- Requests for authentication-related endpoints (`/api/auth/**`) are redirected to the **Authentication API** on **port 8081**.
- Requests for operations endpoints (`/api/operations/**`) are directed to the **Operations API** on **port 8082**.

### Request Flow
1. **Authentication**: Login and user registration requests are routed to the Authentication API via the Gateway.
2. **Mathematical Operations**: Requests for operations are verified and routed to the Operations API via the Gateway.
3. **Central Routing**: All external requests are handled by the Gateway API on **port 8080**, which directs each request to the correct service.

## Technologies Used
- **Spring Boot**: For building REST APIs.
- **Spring Cloud Gateway**: For routing and load balancing.
- **Maven**: Build and dependency management.

## Setup and Execution Instructions
1. **Start the Authentication API**: 
   ```bash
   ./mvnw spring-boot:run
