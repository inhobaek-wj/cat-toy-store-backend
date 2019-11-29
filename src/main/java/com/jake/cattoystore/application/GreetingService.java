package com.jake.cattoystore.application;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String getMessage(String name) {
        if (name == null) {
            return "Hello";
        }
        return "Hello, " + name;
    }
}
