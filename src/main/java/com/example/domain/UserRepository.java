package com.example.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, String> {

	Mono<User> findByFirstName(String firstName);

	Flux<User> findByLastName(String lastName);

}
