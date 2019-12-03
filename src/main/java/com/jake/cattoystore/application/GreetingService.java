package com.jake.cattoystore.application;

import com.jake.cattoystore.domain.Greeting;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String getMessage(String name) {
        // if (name == null) {
        //     return "Hello";
        // }
        // return "Hello, " + name;

        // application layer uses Greeting domain model,
        // so when you need to change business logic, you can change domain model.
        Greeting greeting = Greeting.builder()
            .name(name)
            .build();
        return greeting.getMessage();
    }
}
