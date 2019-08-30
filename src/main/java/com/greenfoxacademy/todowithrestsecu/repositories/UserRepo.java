package com.greenfoxacademy.todowithrestsecu.repositories;

import com.greenfoxacademy.todowithrestsecu.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {

  Optional<User> findUserByUsername(String Username);

  Optional<User> findById(Long id);

  Optional<User> findUserByEmail(String email);
}
