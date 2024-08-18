package com.advaya.userservice.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Role extends BaseModel{

    private String roleCode;
    private String roleDesc;
}
