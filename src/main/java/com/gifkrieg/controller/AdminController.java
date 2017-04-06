package com.gifkrieg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by robbie on 4/5/17.
 */
@Controller
public class AdminController {

    Logger log = LoggerFactory.getLogger(getClass().getName());

    @RequestMapping("/admin")
    public String adminRoot(Model model, HttpSession session) {
        log.info("Admin method called");
        return "something";
    }

    @RequestMapping("/admin/test")
    public String adminTest(Model model, HttpSession session) {
        log.info("Admin test method called");
        return "admin test";
    }
}
