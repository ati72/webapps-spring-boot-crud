# What's this?
This repository is for my Web Applications university class. In this project I used Spring Boot to access an SQL Database, and perform CRUD operations through both a view created with Thymeleaf and REST API endpoints. The project has two controllers, one for the Thymeleaf GUI and one for the REST API endpoints. Form inputs are validated, exceptions are handled.

# Technologies used
- Framework: Spring Boot
- Database: MySQL
- View Templates: Thymeleaf

# Endpoints
Base URL: ```http://localhost:8080/api```

### GET Request Endpoints
- ```/players``` Returns all players in the database.
- ```/players/{id}``` Returns a singular player of this {id}.
-```/players/minHeight/{playerMinHeight}``` Returns all players who have a height greater thank {playerMinHeight}.

### POST Request Endpoints
- ```/players``` Adds a player to the database.

### PUT Request Endpoints
- ```/players/{playerId}``` Updates player of id {playerId}.

### DELETE Request Endpoints
- ```/players/{playerId}``` Deletes player of id {playerId}.
