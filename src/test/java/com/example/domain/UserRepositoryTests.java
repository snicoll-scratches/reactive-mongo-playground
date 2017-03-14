package com.example.domain;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserRepositoryTests {

	@Autowired
	private UserRepository repository;

	@Test
	public void findByFirstName() {
		String uniqueFirstName = UUID.randomUUID().toString();
		User user = this.repository.save(new User(uniqueFirstName, "test")).block();
		User found = this.repository.findByFirstName(uniqueFirstName).block();
		assertThat(found).isNotNull();
		assertThat(found.getId()).isEqualTo(user.getId());
		assertThat(found.getFirstName()).isEqualTo(uniqueFirstName);
		assertThat(found.getLastName()).isEqualTo("test");
	}

}
