# Edufy Genre
[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen.svg)](https://spring.io/projects/spring-boot)

## 🎨Overview:
Edufy Genre manages all genres used across the Edufy platform.  
It stores the list of available genres, keeps track of which media (songs, videos, podcast episodes) belong to each genre,  
and is used by all media services when registering new content.

It's part of a bigger project and communicates with other microservices through  
Docker Compose. Other related projects will be linked below.

## 🧩 Related projects
### Organisation
- EdufyProjects - All repositories in one place

### Connections
- [Edufy-infra](https://github.com/EudfyProjects/Edufy-infra) - Contains `docker-compose.yml` file and `init.db` file
- [EudfyEurekaServer](https://github.com/Sommar-skog/EdufyEurekaServer) - Server that connects the services instances
- [Gateway](https://github.com/SaraSnail/EdufyGateway) - Funnels all requests with one base endpoint
- [EdufyUser](https://github.com/Jamtgard/EdufyUser) - Holds in the Users and can connect to Keycloak to create new ones
- [EdufyKeycloak](https://github.com/Sommar-skog/EdufyKeycloak) - A pipeline for Azure but had to switch to local container
### Media connections
- [EdufyCreator](https://github.com/Sommar-skog/EdufyCreator) - Holds the Creators for all the media services
- [EdufyGenre](https://github.com/a-westerberg/EdufyGenre) - Holds all the Genres for the microservices
- [EdufyThumb](https://github.com/a-westerberg/EdufyThumb) - Records of thumbs up and down on media
- [EdufyUtility](https://github.com/a-westerberg/EdufyUtility) - No code so far but was created to hold algorithms to extract top 10 for a User
### Media services
- [EdufyMusic](https://github.com/Jamtgard/EdufyMusic) - Service for songs and albums
- [EdufyVideo](https://github.com/Sommar-skog/EdufyVideo) - Service for video clips and video playlists
- [EdufyPod](https://github.com/SaraSnail/EdufyPod) - Service for podcast episodes and seasons

---

## 🚀 Tech Stack

- **Language :** Java 21
- **Build Tool :** Apache Maven
- **Framework :** Spring Boot 3.5.7
    - Spring Data JPA
    - Spring basic security
    - Spring Web
    - Eureka Client
    - Spring Cloud LoadBalancer
- **Databases :**
    - MYSQL 8.0 (Docker compose)
    - H2 (development)
- **Security :**
    - Spring Security
    - OAuth2 Resource Server
- **Testing :**
    - Spring Boot Testing
    - Mockito
    - JUnit 5

---

## 🏁 Getting started
### Prerequisites

- Java 21
- Maven
- Docker
- Postman
- Keycloak

---

### 🔌 Ports
#### Connections
- **Eureka :** `8761`
- **Gateway :** `4545`
- **MySQL :** `3307`
- **User :** `8686`
- **Keycloak :** `8080`

#### Media connections
- **Creator :** `8787`
- **Genre :** `8585`
- **Thumb :** `8484`
- **Utility :** `8888`

#### Media services
- **Pod :** `8282`
- **Video :** `8383`
- **Music :** `8181`

---

## 🔒 Authentication & Roles

This service uses **OAuth2** and **Keycloak** for authentication and authorization.

### User Roles:
- **edufy_realm_admin** – Can reach all admin endpoints across all microservices
- **genre_admin** – Can create new genres and access all admin endpoints
- **genre_user** – Can view genres
- **microservice_access** – Used internally between microservices

> _Note: These roles are placeholders for development and not production users._

| Role                | Username          | Password |
|---------------------|:-----------------:|:--------:|
| genre_admin         |   genre_admin     |  admin   |
| edufy_realm_admin   | edufy_realm_admin |  admin   |
| genre_user          |    genre_user     |  genre   |
| microservice_access |                   |          |

> Note: Unauthenticated requests will receive a `401 Unauthorized` response.  
> `microservice_access` is a client role used internally between services.

---

## 📚 API Endpoints

### Admin – Roles `genre_admin` & `edufy_realm_admin`:
* **GET** `/genre/all` – Gets all genres
* **POST** `/genre/create` – Creates a new genre (request body required)

### Client – Role `microservice_access`:
* **POST** `/genre/media/record` – Registers a mediaId to one or more genres
* **GET** `/genre/by/media-type/{mediaType}` – Gets all genres used by the given media type

### Common – Authenticated:
* **GET** `/genre/all` – Lists all genres (output may differ based on role)

---

## 🐳 Docker
- Use `docker-compose.yml` from Edify-infra to build and run the project
- Docker network: `edufy-network`

---

## 🛢️ MySQL Database
Note: These are not "real" users/admin. They are placeholders for production and used under development.
| Name             | Username | Password |   Database    |
|------------------|:--------:|:--------:|:-------------:|
| edufy_mysql      |   assa   |   assa   | main database |
| edufy_genre_db   |   assa   |   assa   |     genre     |

- **Version:** 8.0
- **SQL file:**
    - Used by docker-compose to create databases and grant access
- **Default Port:** `3306`
    - Mapped as `3307:3306` by default

---
> _README made by https://github.com/a-westerberg_