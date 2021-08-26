package com.orbsec.photobackendusersapi.domain;

import lombok.Data;


@Data
public class UserResponse {

    private String firstName;

    private String lastName;

    private String email;

    private String userId;

}
