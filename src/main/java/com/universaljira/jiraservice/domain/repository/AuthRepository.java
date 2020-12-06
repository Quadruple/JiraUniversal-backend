package com.universaljira.jiraservice.domain.repository;

import com.universaljira.jiraservice.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface AuthRepository extends MongoRepository<User, String> {
    Optional<User> findById(String userName);
}
