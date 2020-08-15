package com.universaljira.jiraservice.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "TotalScoreCollection")
public class TotalScore {
    @Id
    private String userName;
    private Double totalScore;

    public TotalScore(String userName, Double totalScore) {
        this.userName = userName;
        this.totalScore = totalScore;
    }
}
