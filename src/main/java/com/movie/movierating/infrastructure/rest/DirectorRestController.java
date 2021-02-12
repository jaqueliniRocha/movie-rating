package com.movie.movierating.infrastructure.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.movie.movierating.model.Director;
import com.movie.movierating.model.DirectorRepository;

@CrossOrigin
@RestController
public class DirectorRestController {

	private final DirectorRepository repository;

	DirectorRestController(DirectorRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/director")
	List<Director> all() {
		return repository.findAll();
	}

	@PostMapping("/director")
	Director registerDirector(@RequestBody Director registerDirector) {
		return repository.save(registerDirector);
	}

	@GetMapping("/director/{id}")
	Director getById(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new DirectorNotFoundException(id));
	}

	@PutMapping("/director/{id}")
	Director update(@RequestBody final Director registerDirector, @PathVariable Long id) {
		return repository.findById(id).map(director -> {
			director.setName(registerDirector.getName());
			director.setCountry(registerDirector.getCountry());
			return repository.save(director);
		}).orElseThrow(() -> new DirectorNotFoundException(id));
	}

	@DeleteMapping("/director/{id}")
	 public ResponseEntity<Void> deleteDirector(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
