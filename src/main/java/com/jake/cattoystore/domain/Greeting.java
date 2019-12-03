package com.jake.cattoystore.domain;

import lombok.Builder;

@Builder
public class Greeting {

    private String name;

    public String getMessage() {
        if (this.name != null) {
            return "Hello, " + name;
        }

        return "Hello";
    }
}
