package com.gifkrieg.controller.api;

import com.gifkrieg.constants.Defaults;
import com.gifkrieg.data.CreditsRepository;
import com.gifkrieg.exception.DuplicateRequestException;
import com.gifkrieg.exception.GifAlreadySubmittedException;
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

    @Autowired
    private CreditsRepository creditsRepository;

    @Transactional
    @RequestMapping(path = "/submission/{challengeId}", method = RequestMethod.POST)
    public ResponseEntity postSubmission(@RequestBody PostSubmissionBody body, @PathVariable int challengeId) {
        log.debug("postSubmission method called");

        GKUserDetails userDetails = (GKUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = userDetails.getUserId();

        Challenge challenge = challengeService.getCurrentChallenge();
        if (challenge.getId() != challengeId) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid.challenge");
        }

        UserGif ug = userGifService.getUserGif(userId, body.getGifId());
        if (ug == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("gif.invalid");
        }

        // necessary? not scalable solution
        synchronized (userDetails.getUsername()) {
            try {
                submissionService.submitSubmission(challengeId, body.getGifId(), userId);
            } catch (DuplicateRequestException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("You've already submitted for this challenge. You shouldn't be here.");
            } catch (GifAlreadySubmittedException e) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("This GIF was already submitted by another user.");
            }
        }

        // remove the gif from the user's inventory
        userGifService.removeGifFromInventory(ug);

        // update stats / counters
        // TODO: maybe this would good for rabbitmq

        // give credits for submitting

        // give user credits for voting
        creditsRepository.addCreditsForUser(userId, Defaults.CREDITS_FOR_SUBMISSION);

        return ResponseEntity.ok(new CreditsResponse(Defaults.CREDITS_FOR_SUBMISSION));
    }


}
