package com.universaljira.jiraservice.application.controller;

import com.universaljira.jiraservice.domain.model.Task;
import com.universaljira.jiraservice.domain.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/createTask")
    public void createTask(@RequestBody Task task) {
        taskService.createTask(task);
    }
}


