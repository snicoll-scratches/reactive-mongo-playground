package com.example.web;

import com.example.domain.User;
import com.example.domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebFluxTest(UserController.class)
public class UserControllerTests {

	@Autowired
	private WebTestClient webClient;

	@MockBean
	private UserRepository repository;

	@Test
	public void findById() {
		User expected = new User("abcd", "John", "Smith");
		given(repository.findById("abcd")).willReturn(Mono.just(expected));
		this.webClient.get().uri("/users/{id}", "abcd")
				.exchange()
				.expectStatus().isOk()
				.expectBody(User.class).isEqualTo(expected);
	}

	@Test
	public void findByIdDoesNotExist() {
		given(repository.findById("does-not-exist")).willReturn(Mono.empty());
		this.webClient.get().uri("/users/{id}", "does-not-exist")
				.exchange().expectStatus().isNotFound();
	}

}
