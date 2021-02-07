package com.universaljira.jiraservice.application.controller;

import com.universaljira.jiraservice.domain.model.TotalScore;
import com.universaljira.jiraservice.domain.service.TotalScoreService;
import com.universaljira.jiraservice.util.ScoreSorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
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
        List<TotalScore> allScores = totalScoreService.getAllTotalScores();
        allScores.sort(new ScoreSorter());
        return allScores;
    }
}
