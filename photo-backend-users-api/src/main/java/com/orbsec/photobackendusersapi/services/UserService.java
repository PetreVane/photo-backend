package com.orbsec.photobackendusersapi.services;

import com.orbsec.photobackendusersapi.domain.User;
import com.orbsec.photobackendusersapi.domain.dto.UserDto;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserByEmail(String email);

    User save(UserDto dto);
}
