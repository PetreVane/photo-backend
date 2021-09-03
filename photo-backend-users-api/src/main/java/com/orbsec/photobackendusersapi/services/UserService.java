package com.orbsec.photobackendusersapi.services;

import com.orbsec.photobackendusersapi.domain.models.CreateUserDto;
import com.orbsec.photobackendusersapi.domain.models.User;
import com.orbsec.photobackendusersapi.domain.models.UserResponseDto;
import com.orbsec.photobackendusersapi.exceptions.UserNotRegistered;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findUserByEmail(String email);

    UserResponseDto save(CreateUserDto dto) throws UserNotRegistered;

    void deleteUser(String email);
}
