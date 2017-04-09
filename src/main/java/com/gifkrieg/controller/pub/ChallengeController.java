package com.gifkrieg.controller.pub;

import com.gifkrieg.model.Challenge;
import com.gifkrieg.service.ChallengeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by robbie on 4/5/17.
 */
@RestController
@RequestMapping("/pub")
public class ChallengeController {
    Logger log = LoggerFactory.getLogger(getClass().getName());

    private static final int RECENT_CHALLENGES = 3;

    @Autowired
    private ChallengeService challengeService;

    @RequestMapping(path = "/challenge", method = RequestMethod.GET)
    public Map getRecentChallenges() {
        log.info("getChallenge method called");
        Map<String, Object> response = new HashMap<>();

        // get current
        Challenge current = challengeService.getCurrentChallenge();
        response.put("current", current);

        // get previous
        int currentId = current.getId();
        List<Challenge> pastChallenges = challengeService.getChallenges(currentId - RECENT_CHALLENGES, currentId - 1);
        response.put("past", pastChallenges);
        return response;
    }

}
