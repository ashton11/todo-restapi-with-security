package com.greenfoxacademy.todowithrestsecu.service;

import com.greenfoxacademy.todowithrestsecu.models.User;
import com.greenfoxacademy.todowithrestsecu.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class JWTUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findUserByUsername(username);

    if (user==null){
      throw new UsernameNotFoundException(String.format("User '%s' not found", username));
    } else {
      return null;
    }
  }
}
