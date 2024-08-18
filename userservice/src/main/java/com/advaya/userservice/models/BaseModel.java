package com.advaya.userservice.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseModel {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @jakarta.persistence.Id
    private Long Id;

    private Boolean isDeleted;
}
