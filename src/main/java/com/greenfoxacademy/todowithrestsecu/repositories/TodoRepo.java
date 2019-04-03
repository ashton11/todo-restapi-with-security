package com.greenfoxacademy.todowithrestsecu.repositories;

import com.greenfoxacademy.todowithrestsecu.models.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepo extends CrudRepository<Todo, Long> {
  public boolean findTodoByIdContaining(long id);
}
