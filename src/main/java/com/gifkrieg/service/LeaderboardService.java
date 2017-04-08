package com.gifkrieg.service;

import com.gifkrieg.model.Score;

import java.util.List;

/**
 * Created by robbie on 4/7/17.
 */
public interface LeaderboardService {

    public List<Score> getTopScores();
}
