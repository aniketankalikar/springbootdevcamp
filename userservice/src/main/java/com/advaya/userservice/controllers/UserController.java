package com.advaya.userservice.controllers;

import com.advaya.userservice.dtos.LoginRequestDTO;
import com.advaya.userservice.dtos.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/signup")
    public UserDTO signUp(@RequestBody LoginRequestDTO loginRequestDTO)
    {

        return null;
    }
}
