package com.gifkrieg.controller.auth;

import com.gifkrieg.model.GKUserDetails;
import com.gifkrieg.model.Result;
import com.gifkrieg.model.User;
import com.gifkrieg.service.SecurityService;
import com.gifkrieg.service.UserService;
import com.gifkrieg.validator.UserValidator;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

/**
 * Created by robbie on 4/4/17.
 */

@RestController
@RequestMapping("/auth")
public class LoginController {
    public static final int START_INVENTORY_SIZE = 3;
    Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result registration(@RequestBody User user) {
        log.debug("New Registration POST received.");

        Map<String, String> errors = userValidator.validate(user);
        if (errors != null && errors.keySet().size() > 0) {
            return new Result("failure", errors);
        }

        // Success! Save the user
        userService.saveNewUser(user);
        securityService.autologin(user.getUsername(), user.getPasswordConfirm());

        // get starting gifs
        user.setInventorySize(START_INVENTORY_SIZE);
        userService.acquireStarterGifs(user);

        GKUserDetails gkUserDetails = (GKUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new Result("success", gkUserDetails);
    }
//
//    @RequestMapping(value = "/regsuccess")
//    public String registerSuccess(Model model, String error, String logout) {
//        log.debug("Registration success method.");
//        return "regsuccess";
//    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(Model model, String error, String logout) {
//        if (error != null)
//            model.addAttribute("error", "Your username and password is invalid.");
//
//        return "login";
//    }

    @RequestMapping("/user")
    public GKUserDetails user(Principal user) {
        log.debug("user() called in LoginController.");
        if (user == null) {
            // unauthenticated
            return null;
        }
        GKUserDetails gkUserDetails = (GKUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//        GKUserDetails userDetails
        return gkUserDetails;
    }

}