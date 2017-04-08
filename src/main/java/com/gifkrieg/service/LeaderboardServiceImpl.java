package com.gifkrieg.service;

import com.gifkrieg.data.StatsRepository;
import com.gifkrieg.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by robbie on 4/7/17.
 */
@Service("leaderboardService")
public class LeaderboardServiceImpl implements LeaderboardService {

    @Autowired
    private StatsRepository scoreRepository;

    @Override
    @Cacheable(value = "leaderboard")
    public List<Score> getTopScores() {
        return scoreRepository.getTopScores();
    }
}
