package com.greenfoxacademy.todowithrestsecu.controllers;

import com.greenfoxacademy.todowithrestsecu.errorHandling.TodoError;
import com.greenfoxacademy.todowithrestsecu.errorHandling.UserError;
import com.greenfoxacademy.todowithrestsecu.models.Todo;
import com.greenfoxacademy.todowithrestsecu.service.TodoService;
import com.greenfoxacademy.todowithrestsecu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoRESTController {
  private TodoService todoService;
  private UserService userService;

  @Autowired
  public TodoRESTController(TodoService service, UserService userService) {
    this.todoService = service;
    this.userService = userService;
  }

  @GetMapping("/api/getAll")
  public List<Todo> getAllThemTodos() throws TodoError {
   return todoService.getAllTodos();
  }

  @PostMapping("/api/addTodo")
  public ResponseEntity addNewTodo(@RequestBody Todo todo, @AuthenticationPrincipal String username) throws TodoError, UserError {
    com.greenfoxacademy.todowithrestsecu.models.User loggedInUser = userService.getUserByUsername(username);
    todo.setUser(loggedInUser);
    todoService.saveTodo(todo);
    return new ResponseEntity(HttpStatus.OK);
  }
}
