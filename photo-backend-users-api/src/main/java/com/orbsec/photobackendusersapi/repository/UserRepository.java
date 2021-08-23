package com.orbsec.photobackendusersapi.repository;

import com.orbsec.photobackendusersapi.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
}