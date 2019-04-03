package com.greenfoxacademy.todowithrestsecu.repositories;

import com.greenfoxacademy.todowithrestsecu.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
  public User findUserByUsername(String Username);
}
