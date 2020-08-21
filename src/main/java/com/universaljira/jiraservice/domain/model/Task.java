package com.universaljira.jiraservice.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "TasksCollection")
public class Task {
    @Id
    private Integer taskId;
    private String userName;
    private String type;
    private String title;
    private String description;
    private double score;

}
