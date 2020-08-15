package com.universaljira.jiraservice.domain.service;

import com.universaljira.jiraservice.application.RequestModel.MoveTaskRequest;
import com.universaljira.jiraservice.domain.model.DropTarget;
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

    public void createTask(Task task) {
        taskRepository.save(task);
    }

    public void moveTask(MoveTaskRequest moveTaskRequest) {

        Optional<Task> maybeTask = taskRepository.findById(moveTaskRequest.getTaskId());

        if(maybeTask.isPresent()) {
            if(shouldTotalScoreChange(moveTaskRequest, maybeTask.get())) {
                Optional<TotalScore> totalScore = totalScoreRepository.findById(maybeTask.get().getUserName());
                updateScore(totalScore, maybeTask.get());
            }

            maybeTask.get().setType(moveTaskRequest.getDropTarget());
            taskRepository.save(maybeTask.get());
        }
    }

    private Boolean shouldTotalScoreChange(MoveTaskRequest moveTaskRequest, Task task) {
        return moveTaskRequest.getDropTarget().equals(DropTarget.DONE.getValue()) ||
                (moveTaskRequest.getDropTarget().equals(DropTarget.INPROGRESS.getValue()) &&
                        task.getType().equals(DropTarget.DONE.getValue()));
    }

    private void updateScore(Optional<TotalScore> totalScore, Task task) {
        if(totalScore.isPresent()) {
            totalScore.get().setTotalScore(totalScore.get().getTotalScore() + task.getScore());
            totalScoreRepository.save(totalScore.get());
        } else {
            TotalScore newTotalScore = new TotalScore(task.getUserName(), task.getScore());
            totalScoreRepository.save(newTotalScore);
        }

    }

    public void deleteTask(Integer taskId) {
        Optional<Task> maybeTask = taskRepository.findById(taskId);

        if(maybeTask.isPresent() && maybeTask.get().getType().equals(DropTarget.DONE.getValue())) {
            Optional<TotalScore> maybeTotalScore = totalScoreRepository.findById(maybeTask.get().getUserName());

            if(maybeTotalScore.isPresent()) {
                totalScoreRepository.save(new TotalScore(maybeTask.get().getUserName(), maybeTotalScore.get().getTotalScore() - maybeTask.get().getScore()));
            }

            taskRepository.deleteById(taskId);
        }
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }
}
