package com.movie.movierating.infrastructure.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.movie.movierating.model.Vote;
import com.movie.movierating.model.VoteRepository;

@CrossOrigin
@RestController
public class VoteRestController {

	@Autowired
	private VoteRepository repository;

	@PostMapping("/vote")
	Vote save(@RequestBody @Valid Vote voteRequest) {
		return repository.save(voteRequest);
	}
}
