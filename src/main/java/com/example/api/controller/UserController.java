package com.example.api.controller;

import com.example.api.services.UserService;
import com.example.api.user.User;
import com.example.api.userDto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody UserDto userDto) throws Exception {
        var newUser = userService.create(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

}
