package com.inzynieria.wisielec.controller;

import com.inzynieria.wisielec.entity.User;
import com.inzynieria.wisielec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody User user){

        System.out.println(user);

         if(!userRepository.findById(user.getUsername()).isPresent())
             userRepository.save(user);
    }

    @GetMapping("/user")
    public User login(@RequestBody User user){
        return userRepository.login(user.getUsername(),user.getPassword());
    }

}
