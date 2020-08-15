package com.universaljira.jiraservice.application.RequestModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MoveTaskRequest {
    private Integer taskId;
    private String dropTarget;
}
