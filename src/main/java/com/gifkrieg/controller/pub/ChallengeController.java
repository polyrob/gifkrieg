package com.gifkrieg.controller.pub;

import com.gifkrieg.model.Challenge;
import com.gifkrieg.service.ChallengeService;
import com.gifkrieg.service.SubmissionService;
import com.gifkrieg.service.VotingService;
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

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private VotingService votingService;

    @RequestMapping(path = "/challenge", method = RequestMethod.GET)
    public Map getRecentChallenges() {
        log.debug("getChallenge method called");
        Map<String, Object> response = new HashMap<>();

        try {
            // get current
            Challenge current = challengeService.getCurrentChallenge();
            response.put("current", current);
            int submissions = submissionService.getSubmissionCountForChallenge(current);
            response.put("currentSubmissions", submissions);

            Challenge voting = challengeService.getVotingChallenge();
            response.put("voting", voting);

            Challenge completed = challengeService.getCompletedChallenge();
            response.put("completed", completed);
            int votes = votingService.getVotesForChallenge(voting);
            response.put("completedVotes", votes);

            // get previous
            int completedId = completed.getId();

            List<Challenge> pastChallenges = challengeService.getChallenges(completedId - RECENT_CHALLENGES, completedId - 1);
            response.put("past", pastChallenges);
        } catch (Exception e) {
            log.error("Exception getting challenges.", e);
        }
        return response;
    }

}
