package com.greenfoxacademy.todowithrestsecu.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenfoxacademy.todowithrestsecu.models.Todo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class JWTUser implements UserDetails {
  private final long id;
  private final String username;
  private final String password;
  private final String name;
  private final List<Todo> userTodos;

  public JWTUser(
          long id,
          String username,
          String password,
          String name,
          List<Todo> userTodos) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.name = name;
    this.userTodos = userTodos;
  }

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
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

  public List<Todo> getUserTodos() {
    return userTodos;
  }
}
