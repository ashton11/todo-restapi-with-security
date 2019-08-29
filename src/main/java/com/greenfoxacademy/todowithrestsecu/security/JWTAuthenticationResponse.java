package com.greenfoxacademy.todowithrestsecu.security;

import java.io.Serializable;

public class JWTAuthenticationResponse implements Serializable {

  private final String token;

  public JWTAuthenticationResponse(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }
}
