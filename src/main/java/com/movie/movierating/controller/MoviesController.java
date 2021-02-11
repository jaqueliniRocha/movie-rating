package com.movie.movierating.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.movie.movierating.model.Movie;
import com.movie.movierating.model.MovieRepository;

@CrossOrigin
@RestController
public class MoviesController {
	
	private final MovieRepository repository;
	
	MoviesController(MovieRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping("/movie")
	Movie registerMovie(@RequestBody Movie registerMovie) {
		return repository.save(registerMovie);
	}
	

	
}
