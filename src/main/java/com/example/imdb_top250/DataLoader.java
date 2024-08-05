package com.example.imdb_top250;

import com.example.imdb_top250.service.ImdbApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ImdbApiService imdbApiService;

    @Override
    public void run(String... arg) throws Exception{
        imdbApiService.fetchAndSaveMovies();
    }
}
