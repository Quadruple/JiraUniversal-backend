package com.universaljira.jiraservice.application.controller;

import com.universaljira.jiraservice.application.ResponseModel.DBResponse;
import com.universaljira.jiraservice.domain.model.User;
import com.universaljira.jiraservice.domain.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {

    AuthService authService;

    @Autowired
    public AuthController(AuthService authService) { this.authService = authService; }

    @PostMapping("/createUser")
    public DBResponse createUser(@RequestBody User user) {
        return authService.createUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
       return authService.login(user);
    }
}
