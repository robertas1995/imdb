package com.example.imdb_top250.controller;

import com.example.imdb_top250.model.Movie;
import com.example.imdb_top250.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/genre/{genre}")
    public List<Movie>getMoviesByGenre(@PathVariable String genre){
        return movieService.getMovieByGenre(genre);
    }
}
