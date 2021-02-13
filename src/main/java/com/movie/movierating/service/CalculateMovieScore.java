package com.movie.movierating.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.movie.movierating.model.Movie;
import com.movie.movierating.model.Vote;
import com.movie.movierating.model.VoteRepository;

@Service
public class CalculateMovieScore {

	@Autowired
	private VoteRepository voteRepository;

	public Double calculate(Movie movie) {
		Collection<Vote> votes = voteRepository.findByMovie(movie);
		if(CollectionUtils.isEmpty(votes)) {
			return null;
		}
		double voteSum = 0.0;
		for(Vote vote : votes) {
			voteSum = voteSum + vote.getScore();
		}
		return voteSum / votes.size();
	}
}
