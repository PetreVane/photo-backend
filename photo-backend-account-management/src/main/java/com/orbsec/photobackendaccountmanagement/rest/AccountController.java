package com.orbsec.photobackendaccountmanagement.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private Environment environment;

    @GetMapping("/status")
    public String getStatus() {
        return "Account microservice is up and running on port " + environment.getProperty("local.server.port");
    }
}
