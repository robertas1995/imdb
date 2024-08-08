# IMDB Top 250 Movies with Spring Boot

This project is a Spring Boot application that fetches the top 250 movies from the [IMDB API](https://raw.githubusercontent.com/theapache64/top250/master/top250.json), saves the data into a MySQL database, and allows querying movies by genre and rating. The application updates the database with the latest top 250 movies once a week.

## Features

- Fetches top 250 movies from a JSON API.
- Saves movie data and genres to a MySQL database.
- Updates movie records if they already exist.
- Provides a REST API to query movies by genre, sorted by rating.

## Technologies

- Spring Boot
- JPA/Hibernate
- MySQL
- REST API
- JSON processing (Jackson)

## Prerequisites

- Java 11 or later
- Maven
- MySQL
- An IDE (like IntelliJ IDEA or Eclipse) (optional but recommended)

## Setup and Installation

### 1. Clone the Repository

```bash
git clone https://github.com/robertas1995/imdb.git
cd imdb

### 2. Configure MySQL Database
  - Create a new database in MySQL(e.g.,'imdb').
  - Update the 'src/main/resources/application.properties' with your MySQL databse
    spring.datasource.url=jdbc:mysql://localhost:3306/imdb
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update

### 3. Build and Run the Application
  - Build the project using Maven: mvn clean install
  - Run the Spring Boot application: mvn spring-boot:run 🏃
### 4. Schedule Updates
  - The application is configured to update the movie database weekly using a scheduled task.
## API Endpoints

### 1.  Fetch Movies by Genre
  - Endpoint: /api/movies/genre/{genre}
  - Method: GET
  - Description: Fetch movies filtered by genre, sorted by rating in descending order.
  - Parameters: genre: The genre to filter movies by.
  - Response:  {
    "id": 1,
    "title": "Movie Title",
    "rating": 8.7,
    "genres": [
      "Action",
      "Adventure"
    ]
  },

## Database Schema
 ###Movie Table
    id (Primary Key, Auto-generated)
    title (String)
    rating (Double)
###Genre Table
    id (Primary Key, Auto-generated)
    name (String)
###Movie-Genre Join Table
    movie_id (Foreign Key)
    genre_id (Foreign Key)

    
  
