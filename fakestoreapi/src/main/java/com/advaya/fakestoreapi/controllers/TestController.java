package com.advaya.fakestoreapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/welcome")
    public String welcome()
    {
        return "Welcome to spring boot dev camp";
    }
}
