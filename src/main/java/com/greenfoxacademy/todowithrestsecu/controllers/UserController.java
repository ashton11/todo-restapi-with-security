package com.greenfoxacademy.todowithrestsecu.controllers;

import com.greenfoxacademy.todowithrestsecu.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @PostMapping
  public ResponseEntity register(@RequestBody User userToRegister){
    
  }
}
