package com.jake.cattoystore.application;


public class GreetingService {

    public String getMessage(String name) {
        if (name == null) {
            return "Hello";
        }
        return "Hello, " + name;
    }
}
