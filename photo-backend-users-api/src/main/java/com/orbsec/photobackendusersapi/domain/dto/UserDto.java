package com.orbsec.photobackendusersapi.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserDto {


    @Size(min = 2, max = 15, message = "First name must be at least 2 characters and maximum 15")
    private String firstName;

    @Size(min = 2, max = 15, message = "Last name must be at least 2 characters and maximum 15")
    private String lastName;

    @NotEmpty
    @Email(message = "Invalid email address")
    private String email;

    @Size(min = 5, message = "Password must be at least 5 characters")
    private String password;
}
