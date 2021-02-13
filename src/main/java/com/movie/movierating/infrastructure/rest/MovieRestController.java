package com.movie.movierating.infrastructure.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.movie.movierating.service.CalculateMovieScore;

@CrossOrigin
@RestController
public class MovieRestController {

	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private CalculateMovieScore calculateMovieScore;

	@GetMapping("/movie")
	List<Movie> all() {
		List<Movie> movies = repository.findAll();
		for(Movie movie : movies) {
			Double score = calculateMovieScore.calculate(movie);
			movie.setScore(score);
			repository.save(movie);
		}
		return movies;
	}

	@PostMapping("/movie")
	Movie save(@RequestBody @Valid Movie movieRequest) {
		return repository.save(movieRequest);
	}

	@GetMapping("/movie/{id}")
	Movie getById(@PathVariable Long id) {
		Movie movie = repository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
		Double score = calculateMovieScore.calculate(movie);
		movie.setScore(score);
		repository.save(movie);
		return movie;
	}

	@PutMapping("/movie/{id}")
	Movie update(@RequestBody @Valid Movie movieRequest, @PathVariable Long id) {
		return repository.findById(id).map(movie -> {
				movie.setTitle(movieRequest.getTitle());
				movie.setYear(movieRequest.getYear());
				movie.setDuration(movieRequest.getDuration());
				movie.setScore(movieRequest.getScore());
				movie.setGenre(movie.getGenre());
				return repository.save(movie);
			}
		).orElseThrow(() -> new MovieNotFoundException(id));
	}

	@DeleteMapping("/movie/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
