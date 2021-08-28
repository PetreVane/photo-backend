package com.orbsec.photobackendusersapi.services;

import com.orbsec.photobackendusersapi.domain.models.CreateUserDto;
import com.orbsec.photobackendusersapi.domain.models.User;
import com.orbsec.photobackendusersapi.exceptions.UserNotRegistered;
import com.orbsec.photobackendusersapi.repository.UserRepository;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User save(CreateUserDto dto) {
        var user = getUserFromDto(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            return userRepository.save(user);
        } catch (Exception exception) {
            throw new UserNotRegistered("Registration failed. A record with this email address already exists");
        }
    }

    private User getUserFromDto(CreateUserDto createUserDto) {
        ModelMapper modelmapper = new ModelMapper();
        modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
       return modelmapper.map(createUserDto, User.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optionalUser = userRepository.findUserByEmail(username);
        if (!optionalUser.isPresent()) throw new UsernameNotFoundException(username);
        var existingUser = optionalUser.get();
        // if enabled is set to 'false' the user will not be able to sign in until he confirms the email address he signs in with
        return new org.springframework.security.core.userdetails.User(existingUser.getEmail(), existingUser.getPassword(), true, true, true, true, new ArrayList<>());
    }
}
