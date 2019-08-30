package com.greenfoxacademy.todowithrestsecu.service;


import com.greenfoxacademy.todowithrestsecu.models.User;
import com.greenfoxacademy.todowithrestsecu.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private UserRepo userRepo;

  @Autowired
  public UserService(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  public void saveUser(User user){
    if (userRepo.findUserByUsername(user.getUsername()).isPresent()) {
      throw new UserCreationError("Username already taken, please choose another!");
    }
    if (userRepo.findUserByEmail(user.getEmail()).isPresent) {
      throw new UserCreationError("Email already taken, please choose another!");
    }
    userRepo.save(user);
  }

  public User getUserByUsername(String username){
    return userRepo.findUserByUsername(username);
  }
}
