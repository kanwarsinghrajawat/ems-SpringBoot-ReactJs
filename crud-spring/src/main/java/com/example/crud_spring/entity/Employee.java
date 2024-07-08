package com.example.crud_spring.entity;

import jakarta.persistence.*;
import lombok.*;


//generate bioler plate code using lombok
//AllArgsConstructor generates a constructor with arguments for all fields.

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    //mapping b/w java object and database tables

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column (name = "age")
    private String age;

//    @Column (name = "role",nullable = false,unique=true)
    @Column (name = "role")
    private String role;

    @Column (name = "dob")
    private String dob;

    @Column (name = "gender")
    private String gender;

    @Column (name = "email")
    private String email;

    @Column (name = "password")
    private String password;


}
