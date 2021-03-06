package com.greenfoxacademy.todowithrestsecu.service;


import com.greenfoxacademy.todowithrestsecu.errorHandling.UserError;
import com.greenfoxacademy.todowithrestsecu.models.User;
import com.greenfoxacademy.todowithrestsecu.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private UserRepo userRepo;

  private BCryptPasswordEncoder pwEncoder;

  @Autowired
  public UserService(UserRepo userRepo, BCryptPasswordEncoder pwEncoder) {
    this.userRepo = userRepo;
    this.pwEncoder = pwEncoder;
  }

  public void saveUser(User user) throws UserError {
    if (userRepo.findUserByUsername(user.getUsername()).isPresent()) {
      throw new UserError("Username already taken, please choose another!");
    }
    if (userRepo.findUserByEmail(user.getEmail()).isPresent()) {
      throw new UserError("Email already taken, please choose another!");
    }
    encodePW(user);
    userRepo.save(user);
  }

  public User getUserByUsername(String username) throws UserError {
    if (userRepo.findUserByUsername(username).isPresent()) {
     return userRepo.findUserByUsername(username).get();
    }
    throw new UserError("User not found!");
  }

  public User login(User userToLogin) throws UserError {
    String username = userToLogin.getUsername();
    if (userRepo.findUserByUsername(username).isPresent()) {
      if (userRepo.findUserByUsername(username).get().getPassword().equals(userToLogin.getPassword())) {
        return userToLogin;
      }
      throw new UserError("Password is incorrect, please try again!");
    }
    throw new UserError("Username not found, please try again!");
  }

  private void encodePW(User user) {
    String encodedPW = pwEncoder.encode(user.getPassword());
    user.setPassword(encodedPW);
  }
}
