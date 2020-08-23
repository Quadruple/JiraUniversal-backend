package com.universaljira.jiraservice.application.ResponseModel;

import com.universaljira.jiraservice.domain.model.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetTasksResponse {
    private DBResponse dbResponse;
    private List<Task> taskList;

    public GetTasksResponse(DBResponse dbResponse, List<Task> taskList) {
        this.dbResponse = dbResponse;
        this.taskList = taskList;
    }
}