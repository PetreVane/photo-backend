package com.orbsec.photobackendusersapi.rest;

import com.orbsec.photobackendusersapi.domain.models.AlbumResponseDto;
import com.orbsec.photobackendusersapi.domain.models.CreateUserDto;
import com.orbsec.photobackendusersapi.domain.models.User;
import com.orbsec.photobackendusersapi.domain.models.UserResponseDto;
import com.orbsec.photobackendusersapi.exceptions.UserAccountNotFound;
import com.orbsec.photobackendusersapi.exceptions.UserNotRegistered;
import com.orbsec.photobackendusersapi.services.AlbumsServiceClient;
import com.orbsec.photobackendusersapi.services.UserService;
import feign.FeignException;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private Environment environment;
    private UserService userService;
    private AlbumsServiceClient albumsServiceClient;

    @Autowired
    public UserController(Environment environment, UserService userService, AlbumsServiceClient albumsServiceClient) {
        this.environment = environment;
        this.userService = userService;
        this.albumsServiceClient = albumsServiceClient;
    }

    @GetMapping("/status")
    public String getStatus() {
        var secretToken = environment.getProperty("token.secret");
        var portNumber = environment.getProperty("local.server.port");
        return "Users microservice is up and running on port " + portNumber + " and secret token " + secretToken;
    }


    @GetMapping(path = "/get/{userID}", produces = {"application/json", "application/xml"})
    public UserResponseDto getUserByID(@PathVariable String userID) throws UserAccountNotFound {
        return userService.findUserById(userID);
    }

    @GetMapping(path = "/{userID}", produces = {"application/json", "application/xml"})
    public UserResponseDto getUserByIdAndAlbums(@PathVariable String userID) throws UserAccountNotFound {

        List<AlbumResponseDto> actualList = albumsServiceClient.findAllAlbums();
        var user = userService.findUserById(userID);
        user.setAlbums(actualList);
        return user;
    }

    @GetMapping(path = "/albums/status")
    public String testRestTemplate() {
        return albumsServiceClient.getStatus();
    }

    @GetMapping(produces = {"application/json", "application/xml"})
    public List<UserResponseDto> getAllUsers() {
        return userService.findAll();
    }


    @PostMapping(path = "/add", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody CreateUserDto createUserDto, AbstractBindingResult bindingResult) throws UserNotRegistered {
        if (bindingResult.hasErrors()) {
            throw new UserNotRegistered("An error has occurred while trying to register user. Check your input values and try again.");
        }

        UserResponseDto userResponseDto = userService.save(createUserDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }


    @PutMapping(path ="/update", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public String updateUser() {
        return "Updating user now ...";
    }


    @DeleteMapping(path ="/delete/{email}", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public String deleteUser(@PathVariable String email) throws UserAccountNotFound {
        userService.deleteUser(email);
        return "User " + email + " has just been deleted...";
    }

}
