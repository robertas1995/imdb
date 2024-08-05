package com.example.imdb_top250;

import com.example.imdb_top250.service.ImdbApiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class ImdbTop250Application {
	public static void main(String[] args) {
		SpringApplication.run(ImdbTop250Application.class, args);
	}
}
