package com.greenfoxacademy.todowithrestsecu.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenfoxacademy.todowithrestsecu.models.Todo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class JWTUser implements UserDetails {
  private long id;
  private String username;
  private String password;
  private String name;
  private List<Todo> userTodos;

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return null;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isEnabled() {
    return true;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Todo> getUserTodos() {
    return userTodos;
  }

  public void setUserTodos(List<Todo> userTodos) {
    this.userTodos = userTodos;
  }
}
