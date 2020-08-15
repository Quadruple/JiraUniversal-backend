package com.universaljira.jiraservice.domain.service;

import com.universaljira.jiraservice.domain.model.Task;
import com.universaljira.jiraservice.domain.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(Task task) {
        taskRepository.save(task);
    }
}
