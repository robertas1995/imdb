package com.example.imdb_top250.repository;

import com.example.imdb_top250.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository <Genre, Long> {
    Genre findByName(String name);
}
