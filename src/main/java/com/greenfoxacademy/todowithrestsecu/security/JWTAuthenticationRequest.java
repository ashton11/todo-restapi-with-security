package com.greenfoxacademy.todowithrestsecu.security;

import java.io.Serializable;

public class JWTAuthenticationRequest implements Serializable {

  private String username;
  private String password;

  public JWTAuthenticationRequest() {super();
  }

  public JWTAuthenticationRequest(String username, String password) {
    this.setUsername(username);
    this.setPassword(password);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }
}
