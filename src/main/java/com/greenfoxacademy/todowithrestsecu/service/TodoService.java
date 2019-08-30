package com.greenfoxacademy.todowithrestsecu.service;

import com.greenfoxacademy.todowithrestsecu.errorHandling.TodoCreationError;
import com.greenfoxacademy.todowithrestsecu.models.Todo;
import com.greenfoxacademy.todowithrestsecu.repositories.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

  private TodoRepo repo;

  @Autowired
  public TodoService(TodoRepo repo) {
    this.repo = repo;
  }

  public void saveTodo(Todo todo) throws TodoCreationError {
    if (checkValidity(todo)) {
    repo.save(todo);
    }
  }

  public List<Todo> getAllTodos(){
    if (repo.findAll().equals(null)) {
      return null;
    }
    return (List<Todo>)repo.findAll();
  }

  private boolean checkValidity(Todo todoToCheck) throws TodoCreationError {
    if (todoToCheck.getTitle().equalsIgnoreCase(null)) {
      throw new TodoCreationError("Todo must have a title");
    }
    if (todoToCheck.getUser().equals(null)){
      throw new TodoCreationError("This todo doesn't belong to anyone, please login");
    }
    return true;
  }


}
