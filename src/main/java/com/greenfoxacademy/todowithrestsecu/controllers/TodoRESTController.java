package com.greenfoxacademy.todowithrestsecu.controllers;

import com.greenfoxacademy.todowithrestsecu.errorHandling.TodoError;
import com.greenfoxacademy.todowithrestsecu.models.Todo;
import com.greenfoxacademy.todowithrestsecu.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoRESTController {
  private TodoService todoService;

  @Autowired
  public TodoRESTController(TodoService service) {
    this.todoService = service;
  }

  @GetMapping("/api/getAll")
  public List<Todo> getAllThemTodos() throws TodoError {
   return todoService.getAllTodos();
  }

  @PostMapping("/api/addTodo")
  public ResponseEntity saveTodo(@RequestBody Todo todo) throws TodoError {
    todoService.saveTodo(todo);
    return new ResponseEntity(HttpStatus.ACCEPTED);
  }
}
