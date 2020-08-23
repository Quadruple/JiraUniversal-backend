package com.universaljira.jiraservice.domain.repository;

import com.universaljira.jiraservice.domain.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public interface TaskRepository extends MongoRepository<Task, String> {
    Optional<Task> findById(String taskId);
}
