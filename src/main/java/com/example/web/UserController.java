package com.example.web;

import com.example.domain.User;
import com.example.domain.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/users/{id}")
	Mono<User> getById(@PathVariable String id) {
		return this.userRepository.findOne(id);
	}

	@GetMapping("/users")
	Flux<User> all() {
		return this.userRepository.findAll();
	}

	@PostMapping("/users")
	Mono<Void> save(@RequestBody Mono<User> user) {
		return this.userRepository.save(user).then();
	}

}
