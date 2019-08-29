package com.greenfoxacademy.todowithrestsecu.service;


import com.greenfoxacademy.todowithrestsecu.models.User;
import com.greenfoxacademy.todowithrestsecu.security.repository.UserRepo;
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
    userRepo.save(user);
  }

  public User getUserByUsername(String username){
    return userRepo.findUserByUsername(username);
  }
}
