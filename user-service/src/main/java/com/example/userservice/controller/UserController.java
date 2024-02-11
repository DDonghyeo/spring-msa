package com.example.userservice.controller;

import com.example.userservice.service.UserService;
import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{user}")
    public ResponseEntity<?> getUser( @PathVariable String user) {
        return ResponseEntity.ok(userService.getUser(user));
    }

}
