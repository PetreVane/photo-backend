package com.orbsec.photobackendusersapi.domain.dto;

import lombok.Data;

import javax.validation.constraints.*;

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
}
