package com.advaya.springbootkafka.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private int id;
    private String firstName;
    private String lastName;


}
