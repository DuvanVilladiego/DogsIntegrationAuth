# Project: REST API to consume the Dog CEO API

## Description

This project consists of creating a REST API that consumes the Dog CEO API to obtain information about dog breeds and convert breed images into base64 format. In addition, a JWT-based authentication system is implemented to protect the endpoints. The project handles error controls to ensure stability and proper service responses.

## Requirements

* Create authentication method (JWT)
* Create endpoint to search dog breeds and convert images to base64
* Handle error controls in the API

## Technologies Used

* **Language**: Java
* **Framework**: Spring Boot
* **Security**: Spring Security with JWT
* **Dependency Management**: Maven
* **Database**: PostgreSQL

## Project Structure

```
src
├── main
│   ├── java
│   │   └── com.example.app
│   │       ├── client
│   │       │   └── AbstractClient.java
│   │       ├── config
│   │       │   ├── AppConfig.java
│   │       │   ├── CorsConfig.java
│   │       │   ├── CustomAuthenticationEntryPoint.java
│   │       │   ├── JWTAuthorizationFilter.java
│   │       │   └── WebSecurityConfig.java
│   │       ├── controller
│   │       │   ├── AuthController.java
│   │       │   ├── DogController.java
│   │       │   ├── impl
│   │       │   │   ├── AuthControllerImpl.java
│   │       │   │   └── DogControllerImpl.java
│   │       │   ├── model
│   │       │   │   ├── UserEntity.java
│   │       │   │   ├── repository
│   │       │   │   └── UserRepository.java
│   │       ├── dto
│   │       │   ├── DogsDTO.java
│   │       │   ├── DogsListWithPagesDTO.java
│   │       │   ├── GeneralResponseWithoutTokenDTO.java
│   │       │   ├── GeneralResponseWithTokenDTO.java
│   │       │   ├── LoginRequestDTO.java
│   │       │   ├── RegisterRequestDTO.java
│   │       │   └── UserDTO.java
│   │       ├── service
│   │       │   ├── AuthService.java
│   │       │   ├── CustomUserDetailsService.java
│   │       │   ├── DogService.java
│   │       │   ├── IntegrationDogService.java
│   │       │   ├── TokenService.java
│   │       │   ├── impl
│   │       │   │   ├── AuthServiceImpl.java
│   │       │   │   ├── CustomUserDetailsServiceImpl.java
│   │       │   │   ├── DogServiceImpl.java
│   │       │   │   ├── IntegrationDogServiceImpl.java
│   │       │   │   └── TokenServiceImpl.java
│   │       ├── utils
│   │       │   ├── Base64Img.java
│   │       │   ├── Constants.java
│   │       │   └── DownloadImg.java
│   │       └── DogsIntegrationAuthApiApplication.java
│   └── resources
│       ├── application.properties
├── test
│   └── java
│       └── com.example.dogapi
│           └── DogsIntegrationAuthApiApplicationTests.java
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## Endpoints

### Register

* **POST /authenticate**
* **Description**: Registers the user in the database and logs the user in, returning a JWT token.
* **Request**:

```json
{
  "email": "test@test.com",
  "password": "password"
}
```

* **Response**:

```json
{
  "status": true,
  "message": "SUCCESS REQUEST",
  "data": null,
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Authentication

* **POST /authenticate**
* **Description**: Authenticates the user and returns a JWT token.
* **Request**:

```json
{
  "email": "test@test.com",
  "password": "password"
}
```

* **Response**:

```json
{
  "status": true,
  "message": "SUCCESS REQUEST",
  "data": null,
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

* **POST /refresh-token**
* **Description**: Verifies a token that is about to expire and returns a new JWT token.
* **Headers**: Authorization: Bearer [token]
* **Response**:

```json
{
  "status": true,
  "message": "SUCCESS REQUEST",
  "data": null,
  "token": "ciDdbGciOiPLOzI1HoIsInE2cCI6IkpXVCJ8..."
}
```

## Get Dog Breeds

* **GET /breeds**
* **Description**: Returns a list of dog breeds with their images encoded in base64.
* **Headers**: Authorization: Bearer [token]
* **Response**:

```json
{
  "status": true,
  "message": "SUCCESS REQUEST",
  "data": [
    {
      "name": "labrador",
      "image": "data:image/jpeg;base64,/9j/4AAQSkZJRgABA..."
    },
    {
      "name": "poodle",
      "image": "data:image/jpeg;base64,/9j/4AAQSkZJRgABA..."
    }
  ]
}
```

## Steps to Run the Project

1. **Clone the Repository**

```bash
git clone https://github.com/DuvanVilladiego/DogsIntegrationAuth.git
```

2. **Access the Project Directory**

```bash
cd DogsIntegrationAuth
```

3. **Project Configuration**

* Run the `usersAuth.sql` scripts.
* Make sure the database configuration in `application.properties` is correct.
* Ensure the `authdog` database exists.

```properties
spring.application.name=dogs-integration-auth-api

# Database configuration
spring.datasource.url=jdbc:postgresql://localhost:5433/authdog
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=org.postgresql.Driver

# Server port configuration
server.port=8082

# Encryption secret key (do not reveal)
SUPER_SECRET_KEY=

# Data source endpoints
DOGS-URL-BREEDS=https://dog.ceo/api/breeds/list/all
DOGS-URL-BREEDS-IMAGES=https://dog.ceo/api/breed/%s/images
```

4. **Set Up the Environment**

Make sure Java and Maven are installed, then install dependencies:

```bash
mvn clean install
```

5. **Run the Application**

```bash
mvn spring-boot:run
```

6. **Test the Endpoints**

You can use tools like Postman to test the endpoints:

* **Register**: POST `http://localhost:8080/authentication/register`
* **Login**: POST `http://localhost:8080/authentication/login`
* **Refresh Token**: POST `http://localhost:8080/authentication/refresh-token`
* **Get Breeds**: GET `http://localhost:8080/dogs/breeds`

> Note: The repository includes a configured Postman collection for testing
> (`Dogs Integration.postman_collection`)

## Error Handling

The API handles common errors such as failed authentication, lack of authorization, and errors when consuming the Dog CEO API. Custom exceptions and global exception handlers are used to send appropriate responses.
