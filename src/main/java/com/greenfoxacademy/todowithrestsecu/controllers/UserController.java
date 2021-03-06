package com.greenfoxacademy.todowithrestsecu.controllers;

import com.greenfoxacademy.todowithrestsecu.errorHandling.UserError;
import com.greenfoxacademy.todowithrestsecu.models.User;
import com.greenfoxacademy.todowithrestsecu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public User register(@RequestBody User userToRegister) throws UserError {
    userService.saveUser(userToRegister);
    return userToRegister;
  }

  @PostMapping("/login")
  public User login(@RequestBody User userToLogin) throws UserError {
    return userService.login(userToLogin);
  }
}
