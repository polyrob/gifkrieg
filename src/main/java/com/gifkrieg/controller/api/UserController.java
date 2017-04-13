package com.gifkrieg.controller.api;

import com.gifkrieg.model.GKUserDetails;
import com.gifkrieg.model.Gif;
import com.gifkrieg.model.UserGif;
import com.gifkrieg.service.GifService;
import com.gifkrieg.service.UserGifService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(path = "/user/gifs", method = RequestMethod.GET)
    public List<Gif> getUserGifs() {
        log.debug("getUserGifs()");
        GKUserDetails userDetails = (GKUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<UserGif> userGifs = userGifService.getUserGifs(userDetails.getUserId());
        List<Gif> gifs = gifService.getGifsForUser(userGifs);

        return gifs;
    }

}
