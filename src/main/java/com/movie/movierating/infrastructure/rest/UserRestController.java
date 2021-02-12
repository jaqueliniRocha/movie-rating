package com.movie.movierating.infrastructure.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.movie.movierating.model.User;
import com.movie.movierating.model.UserRepository;
import com.movie.movierating.service.UserService;

@CrossOrigin
@RestController
public class UserRestController {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserService userService;

	@GetMapping("/user")
	List<User> all() {
		return repository.findAll();
	}

	@PostMapping("/user")
	User newUser(@RequestBody User newUser) {
		return userService.save(newUser);
	}

	@GetMapping("/user/{id}")
	User findById(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	@PutMapping("/user/{id}")
	User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
		return repository.findById(id).map(user -> {
			user.setName(newUser.getName());
			user.setCpf(newUser.getCpf());
			user.setEmail(newUser.getEmail());
			user.setPassword(newUser.getPassword());
			return repository.save(user);
		}).orElseGet(() -> {
			newUser.setId(id);
			return repository.save(newUser);
		});
	}

	@DeleteMapping("/user/{id}")
	void deleteUser(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
