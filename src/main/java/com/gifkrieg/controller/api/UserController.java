package com.gifkrieg.controller.api;

import com.gifkrieg.model.GKUserDetails;
import com.gifkrieg.model.UserGif;
import com.gifkrieg.service.UserGifService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
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

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public List<UserGif> getDetailsForUser(@PathVariable("id") int userId, Principal principal) {
        log.debug("getDetailsForUser(), userId: " + userId);
        GKUserDetails userDetails = (GKUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<UserGif> userGifs = userGifService.getUserGifs(userDetails.getUserId());

        return userGifs;
    }

}
