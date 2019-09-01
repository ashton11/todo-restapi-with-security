package com.greenfoxacademy.todowithrestsecu.security;

import com.greenfoxacademy.todowithrestsecu.models.User;
import com.greenfoxacademy.todowithrestsecu.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private UserRepo repo;


  @Autowired
  public UserDetailsServiceImpl(UserRepo repo) {
    this.repo = repo;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (repo.findUserByUsername(username).isPresent()) {
      User userFromDB = repo.findUserByUsername(username).get();
      return new org.springframework.security.core.userdetails.User(userFromDB.getUsername(),
              userFromDB.getPassword(), Collections.emptyList());
    }
    return null;
  }
}