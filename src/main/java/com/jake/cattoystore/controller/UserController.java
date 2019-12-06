package com.jake.cattoystore.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.github.dozermapper.core.Mapper;
import com.jake.cattoystore.application.UserService;
import com.jake.cattoystore.domain.User;
import com.jake.cattoystore.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // @Autowired
    // private Mapper mapper;

    @PostMapping("/users")
    public ResponseEntity<?> signup(
                                    @RequestBody UserDto userDto
                                    ) throws URISyntaxException {

        // userService.register(mapper.map(userDto, User.class));
        userService.register(userDto);

        URI location = new URI("/");
        return ResponseEntity.created(location).build();
    }

}
