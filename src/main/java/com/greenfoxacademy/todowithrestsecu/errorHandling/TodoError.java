package com.greenfoxacademy.todowithrestsecu.errorHandling;

public class TodoError extends Exception {

  public TodoError(String errorMsg) {
    super(errorMsg);
  }
}
