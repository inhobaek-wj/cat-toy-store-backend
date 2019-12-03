package com.jake.cattoystore.controller;

import com.jake.cattoystore.application.GreetingService;
import com.jake.cattoystore.dto.GreetingDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/hello")
    public GreetingDto hello(
                             @RequestParam(required = false) String name
                             ) {
        GreetingDto greetingDto = new GreetingDto();
        greetingDto.setName("jake");
        greetingDto.setMessage(greetingService.getMessage(name));
        return greetingDto;
    }
}
