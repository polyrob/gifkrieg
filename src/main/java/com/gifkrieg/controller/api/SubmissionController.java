package com.gifkrieg.controller.api;

import com.gifkrieg.model.*;
import com.gifkrieg.service.ChallengeService;
import com.gifkrieg.service.SubmissionService;
import com.gifkrieg.service.UserGifService;
import com.gifkrieg.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

/**
 * Created by robbie on 4/8/17.
 */
@RestController
@RequestMapping("/api")
public class SubmissionController {
    Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private UserGifService userGifService;

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private UserService userService;

    @Transactional
    @RequestMapping(path = "/submission/{challengeId}", method = RequestMethod.POST)
    public ResponseEntity postSubmission(@RequestBody PostSubmissionBody body, @PathVariable int challengeId) {
        log.debug("postSubmission method called");

        GKUserDetails userDetails = (GKUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Challenge challenge = challengeService.getCurrentChallenge();
        if (challenge.getId() != challengeId) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid.challenge");
        }

        UserGif ug = userGifService.getUserGif(userDetails.getUserId(), body.getGifId());
        if (ug == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("gif.invalid");
        }

        // necessary? not scalable solution
        synchronized (userDetails.getUsername()) {
            try {
                submissionService.submitSubmission(challengeId, body.getGifId(), userDetails.getUserId());
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("invalid.alreadySubmitted");
            }
        }

        // remove the gif from the user's inventory
        userGifService.removeGifFromInventory(ug);

        // update stats / counters
        // TODO: maybe this would good for rabbitmq

        return ResponseEntity.ok("Submission accepted.");
    }


}
