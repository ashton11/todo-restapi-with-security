package com.greenfoxacademy.todowithrestsecu.errorHandling;

public class TodoCreationError extends Exception {

  public TodoCreationError(String errorMsg) {
    super(errorMsg);
  }
}
