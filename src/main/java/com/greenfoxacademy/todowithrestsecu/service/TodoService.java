package com.greenfoxacademy.todowithrestsecu.service;

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

  public void saveTodo(Todo todo){
    repo.save(todo);
  }

  public List<Todo> getAllTodos(){
    return (List<Todo>)repo.findAll();
  }

  public boolean exists(Todo todo){
    return repo.findTodoByIdContaining(todo.getId());
  }
}
