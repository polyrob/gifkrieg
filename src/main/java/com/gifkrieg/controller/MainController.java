package com.gifkrieg.controller;

import com.gifkrieg.service.ChallengeService;
import com.gifkrieg.service.GifService;
import com.gifkrieg.service.UserGifService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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

//
//    @RequestMapping("/")
//    public ModelAndView index(Model model, HttpSession session) {
//        log.debug("Main index() controller");
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.set
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
//            log.debug("User is logged in.");
//            GKUserDetails userDetails = (GKUserDetails) auth.getPrincipal();
//            int userId = userDetails.getUserId();
//
//            List<UserGif> userGifs = userGifService.getUserGifs(userId);
//            model.addAttribute("userGifs", userGifs);
//            model.addAttribute("userGifsCount", userGifs.size());
//        } else {
//            log.debug("User NOT logged in.");
//        }
//        model.addAttribute("currentChallenges", challengeService.getCurrentAndPastChallenges(1));
//
//        model.addAttribute("gifs", gifService.getAllGifs());
//
//        //userGifService.getUserGifs(auth.getPrincipal())
//
//        return "index.html";
//    }
}
