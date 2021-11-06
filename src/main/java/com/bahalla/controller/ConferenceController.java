package com.bahalla.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConferenceController {

    @GetMapping("/about")
    public String about() {
        return "This conference is about security and tests";
    }

    @GetMapping("/greeting")
    public String greetings(@AuthenticationPrincipal(expression = "username") String username) {
        return "Hello, " + username + "!";
    }
}
