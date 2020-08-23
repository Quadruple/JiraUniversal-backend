package com.universaljira.jiraservice.domain.service;

import com.universaljira.jiraservice.application.RequestModel.MoveTaskRequest;
import com.universaljira.jiraservice.application.ResponseModel.DBResponse;
import com.universaljira.jiraservice.domain.model.TaskType;
import com.universaljira.jiraservice.domain.model.Task;
import com.universaljira.jiraservice.domain.model.TotalScore;
import com.universaljira.jiraservice.domain.repository.TaskRepository;
import com.universaljira.jiraservice.domain.repository.TotalScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class TaskService {

    TaskRepository taskRepository;
    TotalScoreRepository totalScoreRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, TotalScoreRepository totalScoreRepository) {
        this.taskRepository = taskRepository;
        this.totalScoreRepository = totalScoreRepository;
    }

    public DBResponse createTask(Task task) {

        try {
            taskRepository.save(task);
            return new DBResponse("success");
        } catch(Exception e) {
            e.printStackTrace();
            return new DBResponse("fail");
        }
    }

    public DBResponse moveTask(MoveTaskRequest moveTaskRequest) {

        Optional<Task> maybeTask;

        try {
            maybeTask = taskRepository.findById(moveTaskRequest.getTaskId());
        } catch(Exception e) {
            e.printStackTrace();
            return new DBResponse("fail");
        }

        //Optional<Task> maybeTask = taskRepository.findById(moveTaskRequest.getTaskId());

        if(maybeTask.isPresent()) {
            if(shouldTotalScoreChange(moveTaskRequest, maybeTask.get())) {
                Optional<TotalScore> totalScore;

                try {
                    totalScore = totalScoreRepository.findById(maybeTask.get().getUserName());
                } catch(Exception e) {
                    e.printStackTrace();
                    return new DBResponse("fail");
                }

                //Optional<TotalScore> totalScore = totalScoreRepository.findById(maybeTask.get().getUserName());
                DBResponse updateScoreResponse = updateScore(totalScore, maybeTask.get(), moveTaskRequest.getDropTarget());

                if(updateScoreResponse.getResult().equals("fail")) {
                    return new DBResponse("fail");
                }
            }

            maybeTask.get().setType(moveTaskRequest.getDropTarget());

            try {
                taskRepository.save(maybeTask.get());
                return new DBResponse("success");
            } catch(Exception e) {
                e.printStackTrace();
                return new DBResponse("fail");
            }
        }
        return new DBResponse("fail");
    }

    private Boolean shouldTotalScoreChange(MoveTaskRequest moveTaskRequest, Task task) {
        return moveTaskRequest.getDropTarget().equals(TaskType.DONE.getValue()) ||
                (moveTaskRequest.getDropTarget().equals(TaskType.INPROGRESS.getValue()) &&
                        task.getType().equals(TaskType.DONE.getValue()));
    }

    private DBResponse updateScore(Optional<TotalScore> totalScore, Task task, String dropTarget) {
        if(totalScore.isPresent()) {
            if(dropTarget.equals(TaskType.DONE.getValue())) {
                totalScore.get().setTotalScore(totalScore.get().getTotalScore() + task.getScore());
            } else if(dropTarget.equals(TaskType.INPROGRESS.getValue())) {
                totalScore.get().setTotalScore(totalScore.get().getTotalScore() - task.getScore());
            }

            try {
                totalScoreRepository.save(totalScore.get());
                return new DBResponse("success");
            } catch(Exception e) {
                e.printStackTrace();
                return new DBResponse("fail");
            }

        } else {
            TotalScore newTotalScore = new TotalScore(task.getUserName(), task.getScore());

            try {
                totalScoreRepository.save(newTotalScore);
                return new DBResponse("success");
            } catch(Exception e) {
                e.printStackTrace();
                return new DBResponse("fail");
            }
        }
    }

    public DBResponse deleteTask(String taskId) {
        //Optional<Task> maybeTask = taskRepository.findById(taskId);
        Optional<Task> maybeTask;

        try {
            maybeTask = taskRepository.findById(taskId);
        } catch(Exception e) {
            e.printStackTrace();
            return new DBResponse("fail");
        }

        if(maybeTask.isPresent()) {
            if(maybeTask.get().getType().equals(TaskType.DONE.getValue())) {
                Optional<TotalScore> maybeTotalScore;

                try {
                    maybeTotalScore = totalScoreRepository.findById(maybeTask.get().getUserName());
                } catch(Exception e) {
                    e.printStackTrace();
                    return new DBResponse("fail");
                }

                if(maybeTotalScore.isPresent()) {
                    try {
                        totalScoreRepository.save(new TotalScore(maybeTask.get().getUserName(), maybeTotalScore.get().getTotalScore() - maybeTask.get().getScore()));
                    } catch(Exception e) {
                        e.printStackTrace();
                        return new DBResponse("fail");
                    }
                }
            }

            try {
                taskRepository.deleteById(taskId);
                return new DBResponse("success");
            } catch(Exception e) {
                e.printStackTrace();
                return new DBResponse("fail");
            }
        }
        return new DBResponse("fail");
    }

    public Optional<List<Task>> getTasks() {
        return Optional.of(taskRepository.findAll());
    }
}
