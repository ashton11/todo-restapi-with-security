package com.greenfoxacademy.todowithrestsecu.security;

import com.greenfoxacademy.todowithrestsecu.models.User;
import com.greenfoxacademy.todowithrestsecu.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

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
      User user = repo.findUserByUsername(username).get();
      return (UserDetails) new User(user.getUsername(), user.getPassword(),user.getName(), user.getEmail(), emptyList());
    }
    return null;
  }
}
