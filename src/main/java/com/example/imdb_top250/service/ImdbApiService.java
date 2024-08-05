package com.example.imdb_top250.service;

import com.example.imdb_top250.model.Genre;
import com.example.imdb_top250.model.Movie;
import com.example.imdb_top250.repository.GenreRepository;
import com.example.imdb_top250.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Service
public class ImdbApiService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    //@Scheduled(cron = "0 0 0 * * SUN") //runs every Sunday at midnight
    public void fetchAndSaveMovies() {
        String apiUrl = "https://raw.githubusercontent.com/theapache64/top250/master/top250.json";
        RestTemplate restTemplate = new RestTemplate();
        Movie[] movies = restTemplate.getForObject(apiUrl,Movie[].class);

            if(movies != null) {
                for (Movie movie : movies){
                    Set<Genre> genres = new HashSet<>();
                        for (String genreName : movie.getGenreNames()){
                            Genre genre = genreRepository.findByName(genreName);
                                if(genre == null){
                                    genre = new Genre();
                                    genre.setName(genreName);
                                    genre = genreRepository.save(genre);
                                }
                                genres.add(genre);
                        }
                        movie.setGenres(genres);
                        movieRepository.save(movie);
                }
            }else {
                System.out.println("negauti filmai");
            }
    }
}
