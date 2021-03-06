package com.gifkrieg.controller.api;

import com.gifkrieg.model.GKUserDetails;
import com.gifkrieg.model.Submission;
import com.gifkrieg.service.ChallengeService;
import com.gifkrieg.service.SubmissionService;
import com.gifkrieg.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by robbie on 4/8/17.
 */
@RestController
@RequestMapping("/api")
public class VotingController {
    private Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private UserService userService;


    @RequestMapping(path = "/challenge/{challengeId}", method = RequestMethod.GET)
    public ResponseEntity getSubmissionsForVoting(@PathVariable int challengeId) {
        log.debug("getSubmissionsForVoting() method called");

        GKUserDetails userDetails = (GKUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Submission> entries = submissionService.getVotableEntriesForChallenge(challengeId, userDetails.getUserId());

        return ResponseEntity.ok(entries);
    }

    @RequestMapping(path = "/challenge/{challengeId}", method = RequestMethod.POST)
    public ResponseEntity postVoteForSubmission(@PathVariable int challengeId, @RequestParam int gifId) {
        log.debug("postVoteForSubmission() method called");

        GKUserDetails userDetails = (GKUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Submission> entries = submissionService.getVotableEntriesForChallenge(challengeId, userDetails.getUserId());

        return ResponseEntity.ok(entries);
    }

}
