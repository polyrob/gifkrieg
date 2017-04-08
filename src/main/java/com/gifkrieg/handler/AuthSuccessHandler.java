package com.gifkrieg.handler;

import com.gifkrieg.model.GKUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by robbie on 4/8/17.
 */

public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private StringRedisTemplate template;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("onAuthenticationSuccess()");
        HttpSession session = request.getSession();

        ValueOperations<String, String> ops = template.opsForValue();
        ops.set("Rob redis template", "test");


        GKUserDetails p = (GKUserDetails) authentication.getPrincipal();
        String username = p.getUsername();
        session.setAttribute("test", 3);
    }
}
