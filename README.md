# CookIt Platform Backend

This repository contains the backend service for the CookIt Platform—a web application for sharing, discovering, and managing recipes and related social features. The backend is built with Spring Boot, Java, and MySQL, and provides a rich REST API.

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

The backend exposes a REST API, structured around the following modules (selected highlights shown):

### User & Social

- **User**
  - `POST   /user/signup` — Register a new user
  - `POST   /user/login` — Login
  - `GET    /user/get/{username}` — Get user profile
  
- **Follow**
  - `POST   /follow/create` — Follow a user
  - `DELETE /follow/delete` — Unfollow a user
  - `GET    /follow/get/num/followers/{username}` — Number of followers for user
  - `GET    /follow/get/num/following/{username}` — Number of users followed by user
  - `GET    /follow/get/followers/{username}` — List of followers
  - `GET    /follow/get/following/{username}` — List of users followed

- **Like**
  - `POST   /like/create` — Like a post
  - `DELETE /like/delete` — Unlike a post
  - `GET    /like/get/all/post/{id}` — Get all users who liked a post
  - `GET    /like/get/all/user/{username}` — Get all liked posts by user

### Recipes & Content

- **Post**
  - `POST   /post/create` — Create a new post (recipe)
  - `POST   /post/update` — Update a post
  - `DELETE /post/delete/{id}` — Delete a post by ID
  - `GET    /post/get/{id}` — Get a post by ID
  - `GET    /post/get/top/{num}` — Get top liked posts
  - `GET    /post/get/recent/{num}` — Get most recent posts
  - `GET    /post/get/all` — Get all posts (optional params: `username`, `difficulty`)
  - `GET    /post/search/posts/{keyword}` — Search posts by keyword

- **Bookmark recipes**
  - `POST /bookmark/create` — Add bookmark
  - `DELETE /bookmark/delete` — Remove bookmark
    
- **Tag**
  - `POST /tag/create` — Create a tag
  - `DELETE /tag/delete` — Delete a tag

- **HasTag**
  - `POST   /hastag/create` — Add a tag to a post
  - `DELETE /hastag/delete` — Remove a tag from a post
  - `GET    /hastag/get/posts/with/tag/{tagName}` — Get posts with a specific tag
  - `GET    /hastag/get/post/tags/{id}` — Get all tags for a post

- **ContainsIngredient**
- `POST   /containsingredient/create` — Add ingredient to post
- `DELETE /containsingredient/delete` — Remove ingredient from post
- `GET    /containsingredient/get/ingredients/post/{id}` — Get ingredients for a post

- **Rate**
  - `POST   /rate/create` — Add a rating for a post
  - `DELETE /rate/delete` — Remove a rating
  - `POST   /rate/update` — Update a rating
  - `GET    /rate/get/all/post/{id}` — All ratings for a post
  - `GET    /rate/get/all/user/{username}` — All ratings by a user
  - `GET    /rate/get/post/averagerating/{id}` — Average rating for a post

- **Comment**
  - `POST   /comment/create` — Add a comment
  - `DELETE /comment/delete` — Delete a comment
  - `GET    /comment/get/all/post/{id}` — Get all comments for a post
  - `GET    /comment/get/all/user/{username}` — Get all comments by a user
  - `GET    /comment/get/num/post/comments/{id}` — Get number of comments for a post

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
   mysql -u <user> -p < "Project Documents/cookit-createdb.sql"
   mysql -u <user> -p < "Project Documents/cookit-insert.sql"
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
