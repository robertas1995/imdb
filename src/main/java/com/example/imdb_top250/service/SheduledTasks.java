package com.example.imdb_top250.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SheduledTasks {

    @Autowired
    private ImdbApiService imdbApiService;

    @Scheduled(cron = "0 0 0 * * SUN") //Every Sunday at midnight
    public void updateMovies(){
        imdbApiService.fetchAndSaveMovies();
    }
}
