package com.movie.movierating.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.movierating.model.User;
import com.movie.movierating.model.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User save(User user) {
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new RuntimeException("Existing user with this email!");
		}
		return userRepository.save(user);
	}
}
