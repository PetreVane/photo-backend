package com.orbsec.photobackendusersapi.domain.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.*;
import java.util.UUID;

@Data
public class UserDto {


    @NotBlank(message = "First name cannot be empty")
    @Size(min = 2, max = 15, message = "First name must be at least 2 characters and maximum 15")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 2, max = 15, message = "Last name must be at least 2 characters and maximum 15")
    private String lastName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 5, max = 15, message = "Password must be at least 5 characters and maximum 15 characters")
    private String password;

    private String userId;

    public UserDto(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userId = UUID.randomUUID().toString();
    }
}
