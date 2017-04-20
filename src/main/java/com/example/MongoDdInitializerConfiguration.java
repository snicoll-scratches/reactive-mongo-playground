package com.example;

import com.example.domain.User;
import com.example.domain.UserRepository;
import reactor.core.publisher.Mono;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("app")
public class MongoDdInitializerConfiguration {

	@Bean
	ApplicationRunner databaseInitialization(UserRepository userRepository) {
		return a -> userRepository.count()
				.flatMap(n -> n == 0 ? userRepository.save(new User("Stephane", "Nicoll")) : Mono.empty())
				.block();
	}

}
