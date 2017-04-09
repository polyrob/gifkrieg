package com.gifkrieg.controller.api.pub;

import com.gifkrieg.model.Score;
import com.gifkrieg.service.LeaderboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by robbie on 4/7/17.
 */
@RestController
@RequestMapping("/api/pub")
public class LeaderboardController {
    Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private LeaderboardService leaderboardService;

    @RequestMapping(path = "/leaderboard", method = RequestMethod.GET)
    public @ResponseBody
    List<Score> getTopUsers() {
        log.info("API getTopUsers method called.");

        // Check cache for leaderboard
        return leaderboardService.getTopScores();
    }

}
