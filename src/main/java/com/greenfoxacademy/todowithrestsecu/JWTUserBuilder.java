package com.greenfoxacademy.todowithrestsecu;

import com.greenfoxacademy.todowithrestsecu.JWTUser;
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
