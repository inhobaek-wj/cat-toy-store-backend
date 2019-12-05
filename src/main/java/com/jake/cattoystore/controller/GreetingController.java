package com.jake.cattoystore.controller;

import com.jake.cattoystore.application.GreetingService;
import com.jake.cattoystore.dto.GreetingDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;

@RestController
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/hello")
    public GreetingDto hello(
                             Authentication authentication,
                             @RequestParam(required = false) String name
                             ) {
        String myName = "jake";

        if (authentication != null) {
            // Claims claims = (Claims) authentication.getPrincipal();
            // myName = claims.get("name", String.class);

            myName = authentication.getName();
        }

        GreetingDto greetingDto = new GreetingDto();
        greetingDto.setName(myName);
        greetingDto.setMessage(greetingService.getMessage(name));
        return greetingDto;
    }
}
