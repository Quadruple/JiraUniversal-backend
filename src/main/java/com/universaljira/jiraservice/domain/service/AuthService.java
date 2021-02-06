package com.universaljira.jiraservice.domain.service;

import com.universaljira.jiraservice.application.ResponseModel.DBResponse;
import com.universaljira.jiraservice.domain.model.User;
import com.universaljira.jiraservice.domain.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthService {

    AuthRepository authRepository;

    @Autowired
    public AuthService(AuthRepository authRepository) { this.authRepository = authRepository; }

    public DBResponse createUser(User user) {
        try {
            Optional<User> maybeUser = authRepository.findById(user.getUserName());
            if(maybeUser.isPresent()) {
                return new DBResponse("User already exists");
            }
            else {
                authRepository.save(user);
                return new DBResponse("success");            }

        }
        catch (Exception e) {
            return new DBResponse("Something went wrong");
        }
    }

    public String login(User user) {
        try {
            Optional<User> maybeUser = authRepository.findById(user.getUserName());
            if(maybeUser.isPresent()) {
                if( maybeUser.get().getPassword().equals(user.getPassword())) {
                    return user.getUserName();
                }
                else {
                    return "Invalid password.";
                }
            }
            else {
                return "User not found.";
            }
        }
        catch(Exception e) {
           return "fail";
        }
    }
}
