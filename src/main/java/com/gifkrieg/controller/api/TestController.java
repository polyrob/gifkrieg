package com.gifkrieg.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by robbie on 4/8/17.
 */
@RestController
@RequestMapping("/api")
public class TestController {
    Logger log = LoggerFactory.getLogger(getClass().getName());

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public ResponseEntity apiTest() {
        log.info("API Test method called");
        return ResponseEntity.ok("Hello from /api/test");
    }
}
