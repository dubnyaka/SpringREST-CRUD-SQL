package com.example.springmvc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String dateOfBirth;
    private String email;
    private String phone;
    private long groupID;
}
