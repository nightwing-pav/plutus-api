package com.gemini.plutus.controller;

import com.gemini.plutus.model.Loan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gemini.plutus.exception.UserNotFoundException;
import com.gemini.plutus.model.User;
import com.gemini.plutus.service.UserDatabaseService;

import java.util.List;

@RestController()
@RequestMapping("/user")
public class UserController {

    private final UserDatabaseService userDatabaseService;

    public UserController(UserDatabaseService userDatabaseService){
        this.userDatabaseService = userDatabaseService;
    }
    
    @GetMapping("")
    public ResponseEntity<User> getAccount(Authentication authentication) throws UserNotFoundException{
        return new ResponseEntity<>(this.userDatabaseService.getUserByUsername(authentication.getName()), HttpStatus.OK);
    }
}
