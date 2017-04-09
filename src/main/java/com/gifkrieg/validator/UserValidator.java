package com.gifkrieg.validator;

import com.gifkrieg.model.User;
import com.gifkrieg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by robbie on 4/4/17.
 */

@Component
public class UserValidator {

    @Autowired
    private UserService userService;

    public Map<String, String> validate(User user) {
        Map<String, String> errors = new HashMap<>();

        // TODO: i lied about the lenghts. fix later

        if (user.getUsername().length() < 3 || user.getUsername().length() > 32) {
            errors.put("username", "Username must be 5 - 32 characters.");
        } else if (userService.findByUsername(user.getUsername()) != null) {
            errors.put("username", "This username is already taken.");
        }

        String email = user.getEmail();
        if (email.length() < 3 || email.length() > 32 || !email.contains("@")) {
            errors.put("email", "This email is invalid.");
        }

        if (user.getPassword().length() < 3 || user.getPassword().length() > 32) {
            errors.put("password", "Password must be 6 - 32 characters.");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.put("passwordConfirm", "Your passwords don't match, hot shot.");
        }
        return errors;
    }


}