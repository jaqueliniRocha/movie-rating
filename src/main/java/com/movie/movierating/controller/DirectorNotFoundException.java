package com.movie.movierating.controller;

public class DirectorNotFoundException extends RuntimeException {

	public DirectorNotFoundException(Long id) {
		super("Could not find movie" + id);
	}
	
}
