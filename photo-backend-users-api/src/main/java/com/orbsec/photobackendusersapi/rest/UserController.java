package com.orbsec.photobackendusersapi.rest;

import com.orbsec.photobackendusersapi.domain.User;
import com.orbsec.photobackendusersapi.domain.UserResponse;
import com.orbsec.photobackendusersapi.domain.dto.UserDto;
import com.orbsec.photobackendusersapi.exceptions.UserNotRegistered;
import com.orbsec.photobackendusersapi.services.UserService;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private Environment environment;
    private UserService userService;

    @Autowired
    public UserController(Environment environment, UserService userService) {
        this.environment = environment;
        this.userService = userService;
    }

    @GetMapping("/status")
    public String getStatus() {
        return "Users microservice is up and running on port " + environment.getProperty("local.server.port");
    }

    @PostMapping(path = "/add", consumes = {"application/json", "application/xml"})
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserDto userDto, AbstractBindingResult bindingResult) throws UserNotRegistered {
        if (bindingResult.hasErrors()) {
            throw new UserNotRegistered("An error has occurred while trying to register user. Check your input values and try again.");
        }
        try {
            var createdUser = userService.save(userDto);
            return new ResponseEntity<>(getResponse(createdUser), HttpStatus.CREATED);
        } catch (UserNotRegistered exception) {
            throw exception; // this will be handled by the ErrorHandler
        }
    }

    private UserResponse getResponse(User user) {
        ModelMapper modelmapper = new ModelMapper();
       return modelmapper.map(user, UserResponse.class);
    }

}
