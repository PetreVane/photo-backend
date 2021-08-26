package com.orbsec.photobackendusersapi.services;

import com.orbsec.photobackendusersapi.domain.User;
import com.orbsec.photobackendusersapi.domain.dto.UserDto;
import com.orbsec.photobackendusersapi.exceptions.RegistrationError;
import com.orbsec.photobackendusersapi.exceptions.UserNotRegistered;
import com.orbsec.photobackendusersapi.repository.UserRepository;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public User save(UserDto dto) {
        var user = mapUserFrom(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            return userRepository.save(user);
        } catch (Exception exception) {
            throw new UserNotRegistered("Registration failed. A record with this email address already exists");
        }
    }

    private User mapUserFrom(UserDto userDto) {
        ModelMapper modelmapper = new ModelMapper();
        modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
       return modelmapper.map(userDto, User.class);
    }

    //    private User createUser(UserDto userDto) {
//        User newUser = new User();
//        newUser.setFirstName(userDto.getFirstName());
//        newUser.setLastName(userDto.getLastName());
//        newUser.setEmail(userDto.getEmail());
//        newUser.setPassword(userDto.getPassword());
//        newUser.setUserId(userDto.getUserId());
//        return newUser;
//    }

}
