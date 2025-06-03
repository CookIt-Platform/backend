# CookIt Platform Backend

This repository contains the backend service for the CookIt Platform—a web application for sharing, discovering, and managing recipes and related social features. The backend is built with Spring Boot, Java, and MySQL, and provides a rich RESTful API.

---

## Table of Contents

- [Project Overview](#project-overview)
- [Database Architecture](#database-architecture)
- [API Overview](#api-overview)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Development & Testing](#development--testing)
- [Project Documentation](#project-documentation)

---

## Project Overview

CookIt enables users to:
- Register and manage accounts
- Create, discover, bookmark, and rate recipes
- Tag recipes and manage ingredients
- Follow other users and interact via comments
- Enjoy robust search and social features

The backend is designed for scalability, modularity, and ease of extension.

---

## Database Architecture

The backend uses MySQL as its primary data store, with a normalized relational schema designed to support social interactions and recipe management.

**Key database entities and relationships include:**
- **User**: Stores user profile and authentication data.
- **Recipe/Post**: Represents a recipe post, linked to users (authors), tags, ingredients, ratings, comments, and bookmarks.
- **Tag/Ingredient**: Dictionaries for tagging and composing recipes.
- **Bookmark**: Tracks which users have saved which recipes.
- **Follow**: Stores follower/following relationships.
- **Comment**: User comments on recipes.
- **Rating**: Stores user-submitted ratings per recipe.

**Database scripts**:
- [`cookit-createdb.sql`](Project%20Documents/cookit-createdb.sql): Create schema and tables
- [`cookit-insert.sql`](Project%20Documents/cookit-insert.sql): Seed sample data
- [`cookit-dropdb.sql`](Project%20Documents/cookit-dropdb.sql): Drop all CookIt tables

**Design diagrams and schema**:
- [Relational Schema (docx)](Project%20Documents/Relational%20Schema.docx)
- [ER Diagram (drawio)](Project%20Documents/CS157a.drawio)
- [Normalization Process](Project%20Documents/Normalization.docx)

---

## API Overview

The backend exposes a RESTful API, structured around the following modules (selected highlights shown):

### User & Social

- **Follow users**
  - `POST /follow/create` — Follow another user
  - `DELETE /follow/delete` — Unfollow
  - `GET /follow/get/num/followers/{username}` — Follower count
  - `GET /follow/get/followers/{username}` — List followers

- **Bookmark recipes**
  - `POST /bookmark/create` — Add bookmark
  - `DELETE /bookmark/delete` — Remove bookmark

### Recipes & Content

- **Tags**
  - `POST /tag/create` — Add a tag
  - `DELETE /tag/delete` — Remove a tag

- **Ingredients**
  - `POST /ingredient/create` — Add ingredient
  - `DELETE /ingredient/delete` — Remove ingredient
  - `GET /ingredient/all` — List all ingredients

- **Ratings**
  - `POST /rate/create` — Rate a recipe
  - `PUT /rate/update` — Update a rating
  - `DELETE /rate/delete` — Remove your rating
  - `GET /rate/average/{postId}` — Average rating for a recipe

- **Comments**
  - `POST /comment/create` — Add a comment
  - `DELETE /comment/delete` — Remove a comment
  - `PUT /comment/update` — Edit comment
  - `GET /comment/user/{username}` — Comments by user
  - `GET /comment/post/{postId}` — Comments on a recipe

> **For a full API reference, see:**  
> - [API Endpoints Document](Project%20Documents/endpoints.docx)  
> - Explore controller, service, and dto/entity classes in `src/main/java/com/cookit/backend/`.

---

## Getting Started

### Prerequisites

- Java 22+
- Maven
- MySQL

### Database Setup

1. Use the provided scripts to set up the schema and seed data:
   ```bash
   mysql -u <user> -p < Project\ Documents/cookit-createdb.sql
   mysql -u <user> -p < Project\ Documents/cookit-insert.sql
   ```

2. Update `src/main/resources/application.properties` with your own MySQL connection info:
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/cookit
   spring.datasource.username=YOUR_DB_USER
   spring.datasource.password=YOUR_DB_PASSWORD
   spring.jpa.hibernate.ddl-auto=update
   ```

### Build and Run

```bash
mvn clean install
mvn spring-boot:run
```
The backend will run at `http://localhost:8080`.

---

## Project Structure

The following directories organize the backend codebase:

```
backend/
└── src/
    └── main/
        └── java/
            └── com/
                └── cookit/
                    └── backend/
                        ├── controller/              # REST API endpoints
                        ├── dto/                     # Data Transfer Objects (request/response payloads)
                        ├── entity/                  # JPA database entity models
                        ├── repository/              # Spring Data JPA repositories
                        ├── response/                # Standardized API response classes
                        ├── service/                 # Business logic/services
                        ├── BackendApplication.java  # Main Spring Boot app entry point
                        └── WebConfig.java           # Web configuration (CORS, etc.)
```


---

## Development & Testing

- Run tests: `mvn test`
- API can be tested using Postman, curl, or any REST client
- Contributions welcome: Fork, branch, PR

---

## Project Documentation

See the `Project Documents` folder for:
- System architecture and ER diagrams
- Presentation slides and project proposal
- SQL scripts for DB setup
- API endpoint reference
- Meeting notes and normalization docs

Quick links:
- [Diagrams & Schema](Project%20Documents/CS157a.drawio)
- [API Endpoints](Project%20Documents/endpoints.docx)
- [DB Scripts](Project%20Documents/cookit-createdb.sql)

---
