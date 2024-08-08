package com.example.imdb_top250.dto;

import java.util.List;

public class MovieDTO {
    private List<Actor> actor;
    private AggregateRating aggregateRating;
    private String contentRating;
    private String context;
    private List<Creator> creator;
    private String datePublished;
    private String description;
    private List<Director> director;
    private String duration;
    private List<String> genre;
    private String image;
    private String keywords;
    private String name;
    private Trailer trailer;
    private String type;
    private String url;

    // Getters and Setters

    public static class Actor {
        private String name;
        private String type;
        private String url;

        // Getters and Setters
    }

    public static class AggregateRating {
        private String bestRating;
        private int ratingCount;
        private double ratingValue;
        private String type;
        private String worstRating;

        // Getters and Setters
    }

    public static class Creator {
        private String name;
        private String type;
        private String url;

        // Getters and Setters
    }

    public static class Director {
        private String type;
        private String url;
        private String name;

        // Getters and Setters
    }

    public static class Trailer {
        private String description;
        private String embedUrl;
        private String name;
        private Thumbnail thumbnail;
        private String thumbnailUrl;
        private String type;
        private String uploadDate;

        // Getters and Setters

        public static class Thumbnail {
            private String contentUrl;
            private String type;

            // Getters and Setters
        }
    }
}
