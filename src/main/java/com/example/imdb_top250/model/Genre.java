package com.example.imdb_top250.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies;

    //Getters and Setters

    public Long getId(){
        return id;
    }
    public void SetId(Long id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String genreName){
        this.name = genreName;
    }
    public Set<Movie> getMovies(){
        return movies;
    }
    public void setMovies(Set<Movie> movies){
        this.movies = movies;
    }



}
