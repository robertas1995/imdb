package com.example.imdb_top250.repository;

import com.example.imdb_top250.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByGenresNameOrderByRatingDesc(String genre);
}
