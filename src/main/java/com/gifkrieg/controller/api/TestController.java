package com.gifkrieg.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by robbie on 4/8/17.
 */
@RestController
@RequestMapping("/api")
public class TestController {
    Logger log = LoggerFactory.getLogger(getClass().getName());

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String apiTest() {
        log.info("API Test method called");
        return "WOrks!";
    }
}