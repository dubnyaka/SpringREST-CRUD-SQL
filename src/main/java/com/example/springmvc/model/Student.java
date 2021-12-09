package com.example.springmvc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.internal.util.stereotypes.Lazy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Имя должно быть задано")
    @Size(min=2, max=30)
    private String firstName;
    @NotEmpty(message = "Фамилия должна быть задана")
    @Size(min=2, max=30)
    private String lastName;
    private String middleName;
    private String dateOfBirth;
    @Email
    private String email;
    private String phone;

    @ManyToOne
    @JoinColumn(name="id_group")
    private StudentGroup group;
}
