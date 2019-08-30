package com.greenfoxacademy.todowithrestsecu.repositories;

import com.greenfoxacademy.todowithrestsecu.models.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepo extends CrudRepository<Todo, Long> {
  boolean findTodoByIdContaining(long id);

  Optional<Todo> findById(long id);

  List<Todo> findAll();
}
