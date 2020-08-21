package com.universaljira.jiraservice.application.controller;

import com.universaljira.jiraservice.domain.model.TotalScore;
import com.universaljira.jiraservice.domain.service.TotalScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/totalScore")
public class TotalScoreController {
    TotalScoreService totalScoreService;

    @Autowired
    public TotalScoreController(TotalScoreService totalScoreService) {
        this.totalScoreService = totalScoreService;
    }

    @GetMapping("/getAllTotalScores")
    public List<TotalScore> getAllTotalScores() {
        return totalScoreService.getAllTotalScores();
    }
}

/*
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

    @PatchMapping("/moveTask")
    public void moveTask(@RequestBody MoveTaskRequest moveTaskRequest) {
        taskService.moveTask(moveTaskRequest);
    }

    @DeleteMapping("/deleteTask/{taskId}")
    public void deleteTask(@PathVariable("taskId") Integer taskId) {
        taskService.deleteTask(taskId);
    }

    @GetMapping("/getTasks")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }
}
 */