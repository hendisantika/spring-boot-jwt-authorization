# Role Based Access Control (RBAC) with Spring Boot and JWT

This repo hosts the source code for the article [**Role Based Access Control (RBAC) with Spring Boot and
JWT**](https://medium.com/geekculture/role-based-access-control-rbac-with-spring-boot-and-jwt-bc20a8c51c15?source=github_source)
.

This example project demonstrates how to use the Spring Boot's inbuilt OAuth2 Resoure Server to authenticate and
authorize REST APIs with JWT. First, we have enabled **JWT authentication** and secondly, have introduced
**Role Based Access Control (RBAC)** by mapping a roles claim in JWT to granted authorities in Spring Security.

Furthermore, provides a "/login" endpoint to generate and issue JWTs upon successful login by the users.

This approach is ideal to be used as the
**backend for a single page application (SPA)** written using a frontend framework like ReactJS, Angular, etc...

## Solution Overview

![Solution Overview](img/authorization_process.png "Solution Overview")

## Role Based Access Control

An example of role based access control.

![RBAC Example](img/rbac_sample.png "Solution Overview")

## JWT Authentication Overview

![Solution Overview](img/solution_overview.png "Solution Overview")

## Getting Started

- Use `mvn clean install` in the project root directory to build the project.
- Run the main class, `com.hendisantika.springboot.jwt.JwtApplication` to start the application.

## Endpoints

- `/login` -> Public endpoint which returns a signed JWT for valid user credentials (username/password)
- `/products` -> Contains several endpoints to add and remove product entities. Protected by JWT authentication and
  authorized based on role.

## Create Keystore PKCS13

```shell
keytool -genkey -alias jwtsigning \
    -keystore keystore.jks \
    -storetype PKCS12 \
    -keyalg RSA \
    -storepass password \
    -validity 730 \
    -keysize 4096 
```

### Request Sample

Login API

```shell
curl --location --request POST 'http://localhost:8080/login' \
--header 'Content-Type: application/json' \
--data-raw '{
   "username": "user1",
   "password": "1234"
}'
```

