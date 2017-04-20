package com.gifkrieg.controller.api;

import com.gifkrieg.model.GKUserDetails;
import com.gifkrieg.model.PostSubmissionBody;
import com.gifkrieg.model.Submission;
import com.gifkrieg.model.Vote;
import com.gifkrieg.service.ChallengeService;
import com.gifkrieg.service.SubmissionService;
import com.gifkrieg.service.UserService;
import com.gifkrieg.service.VotingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    private VotingService votingService;

    @Autowired
    private UserService userService;


    @RequestMapping(path = "/challenge/{challengeId}", method = RequestMethod.GET)
    public ResponseEntity getSubmissionsForVoting(@PathVariable int challengeId) {
        log.debug("getSubmissionsForVoting() method called");

        GKUserDetails userDetails = (GKUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Submission> entries = votingService.getVotableEntriesForChallenge(challengeId, userDetails.getUserId());

        return ResponseEntity.ok(entries);
    }

    @RequestMapping(path = "/challenge/{challengeId}", method = RequestMethod.POST)
    public ResponseEntity postVoteForSubmission(@PathVariable int challengeId, @RequestBody PostSubmissionBody gifId) {
        log.debug("postVoteForSubmission() method called");
        GKUserDetails userDetails = (GKUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = userDetails.getUserId();

        // ensure user has not voted in this round yet
        if (votingService.hasUserVotedInRound(userId, challengeId)) {
            return ResponseEntity.badRequest().body("User has already voted");
        }

        votingService.castVoteForSubmission(challengeId, gifId.getGifId(), userId);

        // get a new gif for the user, if they have room in their inventory.

        return ResponseEntity.ok(null);
    }

}
