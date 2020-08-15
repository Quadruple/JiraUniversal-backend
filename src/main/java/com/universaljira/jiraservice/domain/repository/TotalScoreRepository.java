package com.universaljira.jiraservice.domain.repository;

import com.universaljira.jiraservice.domain.model.TotalScore;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface TotalScoreRepository extends MongoRepository<TotalScore, String> {
    Optional<TotalScore> findById(String userName);
}
