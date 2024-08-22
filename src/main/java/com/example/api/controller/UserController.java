package com.example.api.controller;

import com.example.api.user.services.CreateUser;
import com.example.api.user.User;
import com.example.api.userDto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final CreateUser createUser;

    public UserController(CreateUser createUser) {
        this.createUser = createUser;
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody UserDto userDto) throws Exception {
        var newUser = createUser.execute(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }




}
