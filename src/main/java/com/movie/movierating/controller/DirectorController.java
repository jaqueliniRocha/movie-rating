package com.movie.movierating.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.movie.movierating.model.Director;
import com.movie.movierating.model.DirectorRepository;

@CrossOrigin
@RestController
public class DirectorController {

	
	private final DirectorRepository repository;
		
	DirectorController(DirectorRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping("/director")
	Director registerDirector(@RequestBody Director registerDirector) {
		return repository.save(registerDirector);
	}
	
	
}
