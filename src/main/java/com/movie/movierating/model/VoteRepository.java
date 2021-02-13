package com.movie.movierating.model;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long>  {
	
	Collection<Vote> findByMovie(Movie movie);

}
