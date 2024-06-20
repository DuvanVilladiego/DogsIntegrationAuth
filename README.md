## Proyecto: API REST para consumir la API de Dog CEO

### Descripción

Este proyecto consiste en crear una API REST que consuma la API de Dog CEO para obtener información sobre razas de perros y convertir las imágenes de las razas en base64. Además, se implementará un sistema de autenticación basado en JWT para proteger los endpoints. El proyecto maneja controles de error para garantizar la estabilidad y la correcta respuesta del servicio.

### Requerimientos

- Crear método de autenticación (JWT)
- Crear endpoint para buscar las razas de perros y convertir las imágenes en base64
- Manejar controles de error en la API

### Tecnologías Utilizadas

- **Lenguaje**: Java
- **Framework**: Spring Boot
- **Seguridad**: Spring Security con JWT
- **Manejo de Dependencias**: Maven
- **Base de Datsos**: PostgresSQL

### Estructura del Proyecto

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
│   │  	    └── DogsIntegrationAuthApiApplication.java
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

### Endpoints

#### Register

- **POST /authenticate**
  - **Descripción**: Registro del usuario en la base de datos y loguea al usuario devolviendo un token JWT.
  - **Request**: 
    ```json
    {
      "email": "test@test.com",
      "password": "password"
    }
    ```
  - **Response**:
    ```json
    {
      "status": true,
      "message": "SUCCESS REQUEST",
      "data": null,
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
    ```
#### Autenticación

- **POST /authenticate**
  - **Descripción**: Autentica al usuario y devuelve un token JWT.
  - **Request**: 
    ```json
    {
      "email": "test@test.com",
      "password": "password"
    }
    ```
  - **Response**:
    ```json
    {
      "status": true,
      "message": "SUCCESS REQUEST",
      "data": null,
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
    ```

- **POST /refresh-token**
  - **Descripción**: Verifica el token a punto de expirar y devuelve un nuevo token JWT.
  - **Headers**: Authorization: Bearer [token]
  - **Response**:
    ```json
    {
      "status": true,
      "message": "SUCCESS REQUEST",
      "data": null,
      "token": "ciDdbGciOiPLOzI1HoIsInE2cCI6IkpXVCJ8..."
    }
    ```

#### Obtener Razas de Perros

- **GET /breeds**
  - **Descripción**: Devuelve una lista de razas de perros con las imágenes en base64.
  - **Headers**: Authorization: Bearer [token]
  - **Response**:
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

### Pasos para Ejecutar el Proyecto

1. **Clonar el Repositorio**

   ```bash
   git clone https://github.com/DuvanVilladiego/DogsIntegrationAuth.git
   ```

2. **Accede Al Directorio Del Proyecto**

   ```bash
   cd DogsIntegrationAuth
   ```

3. **Configuraciones Del Proyecto:**
	  
   	- Ejecute los scripts `usersAuth.sql`.
	- Asegúrate de que la configuración de la base de datos en `application.properties` sea correcta.
	- Asegúrate de tener la base de datos `authdog` creada.
 
     
    ```properties
	spring.application.name=dogs-integration-auth-api
	
	# Configuración de la base de datos
	spring.datasource.url=jdbc:postgresql://localhost:5433/authdog
	spring.datasource.username=
	spring.datasource.password=
	spring.datasource.driver-class-name=org.postgresql.Driver
	
	# Configuracion del puerto
	server.port=8082
	
	# CLAVE SECRETA DE ENCRIPTACION (no revelar)
	SUPER_SECRET_KEY=
	
	# Endpoint fuente de datos
	DOGS-URL-BREEDS=https://dog.ceo/api/breeds/list/all
	DOGS-URL-BREEDS-IMAGES=https://dog.ceo/api/breed/%s/images
    ```
    
4. **Configurar el Entorno**

   Asegúrate de tener instalado Java y Maven. Luego, instala las dependencias:

   ```bash
   mvn clean install
   ```

5. **Ejecutar la Aplicación**

   ```bash
   mvn spring-boot:run
   ```

6. **Probar los Endpoints**

   Puedes usar herramientas como Postman para probar los endpoints:

   - **Registro**: Envía una petición POST a `http://localhost:8080/authentication/register` con el payload mencionado.
   - **Login**: Envía una petición POST a `http://localhost:8080/authentication/login` sin payload.
   - **Login**: Envía una petición POST a `http://localhost:8080/authentication/refresh-token` con el token JWT en los headers.
   - **Obtener Razas**: Envía una petición GET a `http://localhost:8080/dogs/breeds` con el token JWT en los headers.
     
  `nota: En el repostorio encontraras una coleccion de postman configurada para realizar pruebas (Dogs Integration.postman_collection)`
### Manejo de Errores

La API maneja errores comunes como autenticación fallida, falta de autorización y errores al consumir la API de Dog CEO. Se utilizan excepciones personalizadas y manejadores de excepciones globales para enviar respuestas adecuadas.
