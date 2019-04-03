package com.greenfoxacademy.todowithrestsecu.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private long id;
  private String username;
  private String password;
  private String name;
  @OneToMany(fetch = FetchType.EAGER)
  List<Todo> userTodos;

  public User() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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
