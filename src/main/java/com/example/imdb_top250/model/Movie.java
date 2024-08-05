package com.example.imdb_top250.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private double rating;

    @ManyToMany
    @JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id"),inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    @Transient
    private String[] genreNames; //for API response

    // Getters and Setters

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public double getRating(){
        return rating;
    }
    public void setRating(double rating){
        this.rating = rating;
    }
    public Set<Genre> getGenres(){
        return genres;
    }
    public void setGenres(Set<Genre> genres){
        this.genres = genres;
    }
    public String[] getGenreNames(){
        return genreNames;
    }
    public void setGenreNames(String[] genreNames){
        this.genreNames = genreNames;
    }


}
