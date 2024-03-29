package com.afisha.event_manager.controllers;

import com.afisha.event_manager.models.User;
import com.afisha.event_manager.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        log.info("enter GET login");
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        log.info("enter GET registration");
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute User user) {
        log.info("enter POST registration");
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String securityUrl() {
        return "hello";
    }
}