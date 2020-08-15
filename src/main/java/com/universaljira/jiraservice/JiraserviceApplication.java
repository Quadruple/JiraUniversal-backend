package com.universaljira.jiraservice;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class JiraserviceApplication {

	public @Bean
	MongoClient mongoClient() {
		return MongoClients.create("mongodb+srv://universalJiraAdmin:universalJiraPass@cluster0.fnfbc.mongodb.net/UniversalJiraDB?retryWrites=true&w=majority");
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoClient(), "UniversalJiraDB");
	}

	public static void main(String[] args) {
		SpringApplication.run(JiraserviceApplication.class, args);
	}

}
