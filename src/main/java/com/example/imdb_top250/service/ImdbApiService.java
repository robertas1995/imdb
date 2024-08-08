package com.example.imdb_top250.service;


import com.example.imdb_top250.model.Genre;
import com.example.imdb_top250.model.Movie;

import com.example.imdb_top250.repository.GenreRepository;
import com.example.imdb_top250.repository.MovieRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ImdbApiService {

    private static final Logger LOGGER = Logger.getLogger(ImdbApiService.class.getName());

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    private static final String IMDB_API_URL = "https://raw.githubusercontent.com/theapache64/top250/master/top250.json";
    //@Scheduled(cron = "0 0 0 * * SUN") //runs every Sunday at midnight
    @Transactional
    public void fetchAndSaveMovies() {
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(IMDB_API_URL, String.class);

        LOGGER.log(Level.INFO, "Fetched JSON Response: {0}", jsonResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Map<String, Object>> movies = objectMapper.readValue(jsonResponse, new TypeReference<List<Map<String, Object>>>(){});

            for (Map<String, Object> movieData : movies) {
                String title = (String) movieData.get("name");

                // Extract the rating value safely
                Map<String, Object> aggregateRating = (Map<String, Object>) movieData.get("aggregateRating");
                Double rating = 0.0;
                if (aggregateRating != null) {
                    Object ratingValue = aggregateRating.get("ratingValue");
                    if (ratingValue != null) {
                        rating = Double.parseDouble(ratingValue.toString());
                    }
                }

                // Handle genre as a list
                List<String> genreNames = (List<String>) movieData.get("genre");

                if (title != null && genreNames != null && !genreNames.isEmpty()) {
                    Set<Genre> genres = new HashSet<>();
                    for (String genreName : genreNames) {
                        Genre genre = genreRepository.findByName(genreName);
                        if (genre == null) {
                            genre = new Genre();
                            genre.setName(genreName);
                            genreRepository.saveAndFlush(genre);  // Save and flush to persist the genre in the current context
                            LOGGER.log(Level.INFO, "Saved new genre: {0}", genreName);
                        } else {
                            genre = genreRepository.saveAndFlush(genre);  // Ensure genre is managed
                        }
                        genres.add(genre);
                    }

                    // Check if movie already exists by title
                    Movie existingMovie = movieRepository.findByTitle(title);
                    if (existingMovie != null) {
                        // Update existing movie
                        existingMovie.setRating(rating);
                        existingMovie.setGenres(genres);  // Update genres
                        movieRepository.save(existingMovie);
                        LOGGER.log(Level.INFO, "Updated movie: {0} with new rating and genres", title);
                    } else {
                        // Create new movie
                        Movie movie = new Movie();
                        movie.setTitle(title);
                        movie.setRating(rating);
                        movie.setGenres(genres);  // Set all genres to the movie

                        movieRepository.save(movie);
                        LOGGER.log(Level.INFO, "Saved new movie: {0} with genres: {1}", new Object[]{title, genreNames});
                    }
                } else {
                    LOGGER.log(Level.WARNING, "Skipping movie due to missing title or genre: {0}", title);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to parse JSON response", e);
        }
    }
}