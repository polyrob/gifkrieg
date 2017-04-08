package com.gifkrieg.controller;

import com.gifkrieg.model.GKUserDetails;
import com.gifkrieg.model.Gif;
import com.gifkrieg.model.UserGif;
import com.gifkrieg.service.ChallengeService;
import com.gifkrieg.service.GifService;
import com.gifkrieg.service.UserGifService;
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
import java.util.List;

/**
 * Created by Rob on 4/2/2017.
 */
@Controller
public class MainController {

    private Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private GifService gifService;

    @Autowired
    private UserGifService userGifService;


    @RequestMapping("/")
    public String index(Model model, HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            log.info("User is logged in.");
            GKUserDetails userDetails = (GKUserDetails) auth.getPrincipal();
            int userId = userDetails.getUserId();

            List<UserGif> userGifs = userGifService.getUserGifs(userId);
            model.addAttribute("userGifs", userGifs);
            model.addAttribute("userGifsCount", userGifs.size());
        } else {
            log.info("User NOT logged in.");
        }
        model.addAttribute("currentChallenges", challengeService.getCurrentAndPastChallenges(1));

        model.addAttribute("gifs", gifService.getAllGifs());

        //userGifService.getUserGifs(auth.getPrincipal())

        return "index";
    }
}
