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
	User save(@RequestBody @Valid User userRequest) {
		return userService.save(userRequest);
	}

	@GetMapping("/user/{id}")
	User getById(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	@PutMapping("/user/{id}")
	User update(@RequestBody @Valid User userRequest, @PathVariable Long id) {
		return repository.findById(id).map(user -> {
			user.setName(userRequest.getName());
			user.setEmail(userRequest.getEmail());
			user.setPassword(userRequest.getPassword());
			return repository.save(user);
		}).orElseThrow(() -> new UserNotFoundException(id));

	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
