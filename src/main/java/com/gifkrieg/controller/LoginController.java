package com.gifkrieg.controller;

import com.gifkrieg.model.User;
import com.gifkrieg.service.SecurityService;
import com.gifkrieg.service.UserService;
import com.gifkrieg.validator.UserValidator;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by robbie on 4/4/17.
 */

@RestController
public class LoginController {
    Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

//    @RequestMapping(value = "/register", method = RequestMethod.GET)
//    public ModelAndView registration() {
////        log.info("Requested registration page.");
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("registration");
////        User user = new User();
////
////        // Set below just to ease development
////        user.setUsername("userX");
////        user.setEmail("test@aol.com");
////
////        modelAndView.addObject("userForm", user);
////        modelAndView.setViewName("register");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
//        log.info("New Registration POST received.");
//        userValidator.validate(userForm, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "register";
//        }
//
//        // Success! Save the user
//        userService.saveNewUser(userForm);
//        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
//
//        // get starting gifs
//        userService.acquireStarterGifs(userForm);
//
//        model.addAttribute("test", "yes it does");
//
//        return "redirect:/regsuccess";
//    }
//
//    @RequestMapping(value = "/regsuccess")
//    public String registerSuccess(Model model, String error, String logout) {
//        log.info("Registration success method.");
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
    public Principal user(Principal user) {
        log.info("user() called in LoginController.");
        return user;
    }

}