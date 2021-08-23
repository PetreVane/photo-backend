package com.orbsec.photobackendusersapi.rest;

import com.orbsec.photobackendusersapi.domain.User;
import com.orbsec.photobackendusersapi.domain.dto.UserDto;
import com.orbsec.photobackendusersapi.services.UserService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserControler {

    private Environment environment;

    private UserService userService;

    @Autowired
    public UserControler(Environment environment, UserService userService) {
        this.environment = environment;
        this.userService = userService;
    }

    @GetMapping("/status")
    public String getStatus() {
        return "Users microservice is up and running on port " + environment.getProperty("local.server.port");
    }

    @PostMapping(path = "/add", consumes = {"application/json", "application/xml"})
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto, AbstractBindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError().toString(),HttpStatus.BAD_REQUEST);
        }
         var createduser = userService.save(createUser(userDto));
        return new ResponseEntity<>("User created ", HttpStatus.CREATED);
    }

    private User createUser(UserDto userDto) {
        User newUser = new User();
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(userDto.getPassword());
        return newUser;
    }

}
