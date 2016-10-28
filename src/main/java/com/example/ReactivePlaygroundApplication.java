package com.example;

import com.example.domain.User;
import com.example.domain.UserRepository;
import reactor.core.publisher.Mono;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
		exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class ReactivePlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactivePlaygroundApplication.class, args);
	}

	@Bean
	ApplicationRunner databaseInitialization(UserRepository userRepository) {
		return a -> userRepository.count()
				.then(n -> n == 0 ? userRepository.save(new User("Stephane", "Nicoll")) : Mono.empty())
				.block();
	}

}
