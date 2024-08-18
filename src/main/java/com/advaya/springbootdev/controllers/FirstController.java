package com.advaya.springbootdev.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class FirstController {


    @GetMapping("/welcome")
    public String welcome()
    {
        return "Welcome to Spring Boot Dev Camp";
    }
}
