package com.shortener.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger slf4jLogger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping
    public String login() {
        slf4jLogger.info("Login page");
        return "login";
    }
}
