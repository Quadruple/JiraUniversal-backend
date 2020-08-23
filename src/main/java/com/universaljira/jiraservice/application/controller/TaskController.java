package com.universaljira.jiraservice.application.controller;

import com.universaljira.jiraservice.application.RequestModel.MoveTaskRequest;
import com.universaljira.jiraservice.application.ResponseModel.DBResponse;
import com.universaljira.jiraservice.application.ResponseModel.GetTasksResponse;
import com.universaljira.jiraservice.domain.model.Task;
import com.universaljira.jiraservice.domain.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/createTask")
    public DBResponse createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PatchMapping("/moveTask")
    public DBResponse moveTask(@RequestBody MoveTaskRequest moveTaskRequest) {
        return taskService.moveTask(moveTaskRequest);
    }

    @DeleteMapping("/deleteTask/{taskId}")
    public DBResponse deleteTask(@PathVariable("taskId") String taskId) {
        return taskService.deleteTask(taskId);
    }

    @GetMapping("/getTasks")
    public GetTasksResponse getTasks() {
        GetTasksResponse getTasksResponse = new GetTasksResponse();
        Optional<List<Task>> taskList = taskService.getTasks();

        if(taskList.isPresent()) {
            getTasksResponse.setTaskList(taskList.get());
            getTasksResponse.setDbResponse(new DBResponse("success"));
        } else {
            getTasksResponse.setDbResponse(new DBResponse("fail"));
        }

        return getTasksResponse;
    }
}


