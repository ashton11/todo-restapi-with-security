package com.greenfoxacademy.todowithrestsecu.errorHandling;

public class UserError extends Exception {

  public UserError(String errorMsg) {
    super(errorMsg);
  }
}
