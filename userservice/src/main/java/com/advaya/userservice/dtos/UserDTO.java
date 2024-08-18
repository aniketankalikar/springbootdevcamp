package com.advaya.userservice.dtos;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private Long Id;
    private String userId;
    private String email;
    private String password;
    private Boolean isEmailVerified;
}
