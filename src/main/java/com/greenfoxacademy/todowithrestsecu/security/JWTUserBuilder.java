package com.greenfoxacademy.todowithrestsecu.security;

import com.greenfoxacademy.todowithrestsecu.models.User;

public final class JWTUserBuilder {

  private JWTUserBuilder() {
  }

  public static JWTUser create(User user){
    return new JWTUser(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getName(),
            user.getUserTodos());
  }
}
