package com.example.imdb_top250.service;

import com.example.imdb_top250.model.Movie;
import com.example.imdb_top250.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie>getMovieByGenre(String genre){
        return movieRepository.findByGenresNameOrderByRatingDesc(genre);
    }
}
