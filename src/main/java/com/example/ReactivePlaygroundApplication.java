package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ReactivePlaygroundApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ReactivePlaygroundApplication.class)
				.profiles("app")
				.run(args);
	}

}
