package com.greenfoxacademy.todowithrestsecu.service;

import com.greenfoxacademy.todowithrestsecu.errorHandling.TodoError;
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

  public void saveTodo(Todo todo) throws TodoError {
    if (checkValidity(todo)) {
    repo.save(todo);
    }
  }

  public List<Todo> getAllTodos() throws TodoError {
    if (repo.findAll().size() == 0) {
      throw new TodoError("Nothing todo left for today!");
    }
    return repo.findAll();
  }

  private boolean checkValidity(Todo todoToCheck) throws TodoError {
    if (todoToCheck.getTitle().length() == 0) {
      throw new TodoError("Todo must have a title!");
    }
    if (todoToCheck.getUser().getUsername().length() == 0){
      throw new TodoError("This todo doesn't belong to anyone, please login!");
    }
    return true;
  }


}
