package com.movie.movierating.infrastructure.rest;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(Long id) {
		super("Could not find product" + id);
	}
}
