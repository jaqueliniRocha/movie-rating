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

import com.movie.movierating.model.Movie;
import com.movie.movierating.model.MovieRepository;

@CrossOrigin
@RestController
public class MovieRestController {

	private final MovieRepository repository;

	MovieRestController(MovieRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/movie")
	List<Movie> all() {
		return repository.findAll();
	}

	@PostMapping("/movie")
	Movie registerMovie(@RequestBody Movie registerMovie) {
		return repository.save(registerMovie);
	}

	@GetMapping("/movie/{id}")
	Movie getById(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
	}

	@PutMapping("/movie/{id}")
	Movie updateMovie(@RequestBody final Movie registerMovie, @PathVariable Long id) {
		return repository.findById(id).map(movie -> {
			movie.setTitle(registerMovie.getTitle());
			movie.setYear(registerMovie.getYear());
			movie.setDuration(registerMovie.getDuration());
			movie.setScore(registerMovie.getScore());
			return repository.save(movie);
		}).orElseThrow(() -> new MovieNotFoundException(id));
	}

	@DeleteMapping("/movie/{id}")
	public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}