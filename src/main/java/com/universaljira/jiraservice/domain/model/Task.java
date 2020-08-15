package com.universaljira.jiraservice.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "UniversalJiraCollection")
public class Task {
    @Id
    private String taskId;
    private String type;
    private String title;
    private String description;
    private double score;

    public Task(String type, String title, String description, double score) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.score = score;
    }
}
