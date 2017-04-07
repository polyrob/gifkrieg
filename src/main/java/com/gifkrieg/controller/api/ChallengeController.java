package com.gifkrieg.controller.api;

import com.gifkrieg.model.Challenge;
import com.gifkrieg.service.ChallengeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by robbie on 4/5/17.
 */
@RestController
@RequestMapping("/api")
public class ChallengeController {
    Logger log = LoggerFactory.getLogger(getClass().getName());


    @Autowired
    private ChallengeService challengeService;

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String apiTest(Model model, HttpSession session) {
        log.info("API Test method called");
        return "something";
    }

    @RequestMapping(path = "/challenge", method = RequestMethod.GET)
    public List<Challenge> getChallenge(@PathVariable("count") long count) {
        log.info("getChallenge method called");
        return challengeService.getCurrentAndPastChallenges(1);
    }

}
