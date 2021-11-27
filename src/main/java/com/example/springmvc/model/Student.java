package com.example.springmvc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private long id;
    private String name;
    private String email;
    private String phone;
    private long groupID;
}
