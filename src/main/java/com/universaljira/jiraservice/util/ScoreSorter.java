package com.universaljira.jiraservice.util;

import com.universaljira.jiraservice.domain.model.TotalScore;
import java.util.Comparator;

public class ScoreSorter implements Comparator<TotalScore> {
    @Override
    public int compare(TotalScore o1, TotalScore o2) {
        return o2.getTotalScore().compareTo(o1.getTotalScore());
    }
}
