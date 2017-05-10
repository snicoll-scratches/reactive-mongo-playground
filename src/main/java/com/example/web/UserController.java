package com.example.web;

import com.example.domain.User;
import com.example.domain.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {

	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/users/{id}")
	Mono<User> getById(@PathVariable String id) {
		return this.userRepository.findById(id)
				.switchIfEmpty(Mono.error(
						new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found")));
	}

	@GetMapping("/users")
	Flux<User> all() {
		return this.userRepository.findAll();
	}

	@PostMapping("/users")
	Mono<Void> save(@RequestBody Mono<User> user) {
		return this.userRepository.saveAll(user).then();
	}

}
