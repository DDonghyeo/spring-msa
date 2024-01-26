package com.example.userservice.controller;

import com.netflix.discovery.converters.Auto;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    Environment environment;

    @GetMapping("/test")
    public String test() {

        String secret = environment.getProperty("jwt.secret");


        return "User Service Test: " +
                "token secret : " +  secret;
    }

}
