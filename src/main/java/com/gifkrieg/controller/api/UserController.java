package com.gifkrieg.controller.api;

import com.gifkrieg.constants.Defaults;
import com.gifkrieg.data.UserDetailsRepository;
import com.gifkrieg.model.*;
import com.gifkrieg.service.GifService;
import com.gifkrieg.service.SubmissionService;
import com.gifkrieg.service.UserGifService;
import com.gifkrieg.service.VotingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by robbie on 4/5/17.
 */
@RestController
@RequestMapping("/api")
public class UserController {
    Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private UserGifService userGifService;

    @Autowired
    private GifService gifService;

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private VotingService votingService;

    @Autowired
    private UserDetailsRepository userDetailsRepository;


    @RequestMapping(path = "/user/gifs", method = RequestMethod.GET)
    public List<Gif> getUserGifs() {
        log.debug("getUserGifs()");
        GKUserDetails userDetails = (GKUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<UserGif> userGifs = userGifService.getUserGifs(userDetails.getUserId());
        List<Gif> gifs = gifService.getGifsForUser(userGifs);

        return gifs;
    }

    @RequestMapping(path = "/user/gifs/getRandom", method = RequestMethod.GET)
    public ResponseEntity getNewRandomGif() {
        log.debug("getNewRandomGif()");
        GKUserDetails userDetails = (GKUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // check credits and inventory size
        UserDetails detail = userDetailsRepository.findByUserId(userDetails.getUserId());
        if (detail.getCredits() < Defaults.CREDITS_PURCHASE_RANDOM_GIF) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Not enough credits");
        }

        List<UserGif> userGifs = userGifService.getUserGifs(userDetails.getUserId());
        if (userGifs.size() >= detail.getInvSize()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No room in inventory");
        }

        Gif newOne = userGifService.getNewRandomGif(userDetails.getUserId(), userGifs);

        return ResponseEntity.ok(newOne);
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public UserState getUserState() {
        log.debug("getUserGifs()");
        GKUserDetails userDetails = (GKUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserState state = new UserState();
        // determine if the user can submit current round
        boolean hasSubmitted = submissionService.hasAlreadySubmittedForCurrentRound(userDetails.getUserId());
        state.setHasSubmittedCurrent(hasSubmitted);
        // determine if user can vote for voting round
        boolean hasVoted = votingService.hasAlreadyVotedForVotingRound(userDetails.getUserId());
        state.setHasVotedCurrent(hasVoted);

        UserDetails details = userDetailsRepository.findByUserId(userDetails.getUserId());
        state.setCredits(details.getCredits());
        state.setInvSize(details.getInvSize());

        return state;
    }
}
