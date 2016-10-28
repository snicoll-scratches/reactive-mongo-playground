package com.example;


import java.util.Collection;
import java.util.Collections;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories
public class MongoConfiguration extends AbstractReactiveMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "reactive";
	}

	@Override
	public MongoClient mongoClient() {
		return MongoClients.create();
	}


	@Override
	protected Collection<String> getMappingBasePackages() {
		return Collections.singleton("com.example.domain");
	}

}

