package com.jake.cattoystore.controller;

import com.jake.cattoystore.application.GreetingService;
import com.jake.cattoystore.dto.Greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/hello")
    public Greeting hello(
                          @RequestParam(required = false) String name
                          ) {
        Greeting greeting = new Greeting();
        greeting.setName("jake");
        greeting.setMessage(greetingService.getMessage(name));
        return greeting;
    }
}
