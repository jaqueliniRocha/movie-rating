package com.movie.movierating.infrastructure.rest;

public class DirectorNotFoundException extends RuntimeException {

	public DirectorNotFoundException(Long id) {
		super("Could not find movie" + id);
	}
	
}
