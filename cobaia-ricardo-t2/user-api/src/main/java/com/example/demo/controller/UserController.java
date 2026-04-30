package com.example.demo.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserService;
import com.example.demo.model.dto.NewUserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.User;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    // Controller é uma camada Superior a Repository
    private final UserRepository userRepository;
    private final UserService userService;
    
    public UserController(
        UserRepository userRepository,
        UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void newUser(@RequestBody NewUserDTO newUser) {
        
        // Princípio: Thin Controller & Fat Model
        userService.registerNewUser(newUser);

    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
