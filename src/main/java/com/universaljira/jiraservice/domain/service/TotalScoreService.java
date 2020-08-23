package com.universaljira.jiraservice.domain.service;

import com.universaljira.jiraservice.domain.model.TotalScore;
import com.universaljira.jiraservice.domain.repository.TotalScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TotalScoreService {
    TotalScoreRepository totalScoreRepository;

    @Autowired
    public TotalScoreService(TotalScoreRepository totalScoreRepository) {
        this.totalScoreRepository = totalScoreRepository;
    }

    public List<TotalScore> getAllTotalScores() {
        return totalScoreRepository.findAll();
    }
}