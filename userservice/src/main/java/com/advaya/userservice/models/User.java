package com.advaya.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel{

    private String userId;
    private String email;
    private String password;
    private Boolean isEmailVerified;

    @ManyToMany
    private List<Role> roles;

}
