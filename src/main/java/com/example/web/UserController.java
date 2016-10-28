package com.example.web;

import com.example.domain.UserRepository;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

}
