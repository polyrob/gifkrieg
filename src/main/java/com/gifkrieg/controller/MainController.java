package com.gifkrieg.controller;

import com.gifkrieg.service.ChallengeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by Rob on 4/2/2017.
 */
@Controller
public class MainController {

    private Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private ChallengeService challengeService;

    @RequestMapping("/")
    public String index(Model model, HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            log.info("User is logged in.");
            model.addAttribute("username", auth.getName());

        } else {
            log.info("User NOT logged in.");
        }

        model.addAttribute("currentChallenges", challengeService.getCurrentAndPastChallenges(1));

        model.addAttribute("roles", auth.getAuthorities());
        model.addAttribute("ROLE_USER");
        log.info(auth.getAuthorities().toString());
        return "index";
    }
}
