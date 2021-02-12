package com.movie.movierating.infrastructure.rest;

public class MovieNotFoundException extends RuntimeException {
	
	public MovieNotFoundException(Long id) {
		super("Could not find movie" + id);
	}
}
