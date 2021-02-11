package com.movie.movierating.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/director")
	List<Director> all(){
		return repository.findAll();
	}
	
	@PostMapping("/director")
	Director registerDirector(@RequestBody Director registerDirector) {
		return repository.save(registerDirector);
	}
	
	
}
