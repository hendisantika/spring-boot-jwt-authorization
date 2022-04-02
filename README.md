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

List Product API

```shell
curl --location --request GET 'http://localhost:8080/products' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsIm5iZiI6MTY0ODkwMTgwNSwicm9sZXMiOiJBRE1JTiBTVEFGRl9NRU1CRVIiLCJleHAiOjE2NDg5ODgyMDUsInVzZXJJZCI6IjEiLCJ1c2VybmFtZSI6InVzZXIxIn0.F8pAV-j3j6YHEjkMy_VerFPjbPHdxWFMEmAYYZozVwebZ8A1ogX6Jz-MeD55l4NPw21meqGqfsJYuCKvs5GJR3syp5HTGAugA8-WvXesjWC6DpRNDW3UrmprhtH0JTA6-O2JoRJUgoxXULmZuxK65WDoOYtcNiLcu2pbd8BvuBn2BNOwVjiZHtFs5R_uS-GFfZQW1VOsHQ7_LXRdKN6G_vK1cy-A2_OGgKfgJWVVOOXShjxDqwAjSLRj6vWVSpTxQjVZ9APh1G0L3xEwk34EmHUKsafSglgTRuctBQ3zd1_Y6rGQtM4ilKpja9wWHsUmXqdjdC-gVfHTQp-u2upqB79ICHTAeTMuVxj-NsjGx6HEoeGiCl75EAfLvdUE-OBMARoVAtULOf_ptgRllV3gj1ylnte-Tmf_0VB8WcaF4Uc6OyVgeX_TBy8O9LR4x16IAZJI7Az4KLly1wWVb_LeP-9QAq5P4jdDmSEXELAiW2UoPmxBCQir2NqwEIPNe2zFDA5BvVWmf2BWAUXuZYL1K_Wd2kSF9b6eHRUE2FoImDntE0V7RsfXMUevcRBatVTpBoZ5_AQI_XIZDwinxj1l5YequC1Nafpoy4c4uqvvLrv2SHMNckmdAibn2MYnnV3sGnY4YfqhSsGYAEZk6Kby9QHlkcHrcgTadnq0XiGN1_g'
```

Add New Product

```shell
curl --location --request POST 'http://localhost:8080/products' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsIm5iZiI6MTY0ODkwMTgwNSwicm9sZXMiOiJBRE1JTiBTVEFGRl9NRU1CRVIiLCJleHAiOjE2NDg5ODgyMDUsInVzZXJJZCI6IjEiLCJ1c2VybmFtZSI6InVzZXIxIn0.F8pAV-j3j6YHEjkMy_VerFPjbPHdxWFMEmAYYZozVwebZ8A1ogX6Jz-MeD55l4NPw21meqGqfsJYuCKvs5GJR3syp5HTGAugA8-WvXesjWC6DpRNDW3UrmprhtH0JTA6-O2JoRJUgoxXULmZuxK65WDoOYtcNiLcu2pbd8BvuBn2BNOwVjiZHtFs5R_uS-GFfZQW1VOsHQ7_LXRdKN6G_vK1cy-A2_OGgKfgJWVVOOXShjxDqwAjSLRj6vWVSpTxQjVZ9APh1G0L3xEwk34EmHUKsafSglgTRuctBQ3zd1_Y6rGQtM4ilKpja9wWHsUmXqdjdC-gVfHTQp-u2upqB79ICHTAeTMuVxj-NsjGx6HEoeGiCl75EAfLvdUE-OBMARoVAtULOf_ptgRllV3gj1ylnte-Tmf_0VB8WcaF4Uc6OyVgeX_TBy8O9LR4x16IAZJI7Az4KLly1wWVb_LeP-9QAq5P4jdDmSEXELAiW2UoPmxBCQir2NqwEIPNe2zFDA5BvVWmf2BWAUXuZYL1K_Wd2kSF9b6eHRUE2FoImDntE0V7RsfXMUevcRBatVTpBoZ5_AQI_XIZDwinxj1l5YequC1Nafpoy4c4uqvvLrv2SHMNckmdAibn2MYnnV3sGnY4YfqhSsGYAEZk6Kby9QHlkcHrcgTadnq0XiGN1_g' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Third Product",
    "description": "Third Product Description"
}'
```

