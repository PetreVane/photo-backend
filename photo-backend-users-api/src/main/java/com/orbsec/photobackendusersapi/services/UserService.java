package com.orbsec.photobackendusersapi.services;

import com.orbsec.photobackendusersapi.domain.User;
import com.orbsec.photobackendusersapi.repository.UserRepository;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User save(User user) {
         return userRepository.save(user);
    }
}
