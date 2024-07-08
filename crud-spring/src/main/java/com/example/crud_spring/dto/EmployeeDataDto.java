package com.example.crud_spring.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

// to transfer the data b/w client and server or response for rest api (dat transfer object)
public class EmployeeDataDto {
    private Long id;
    private String name;
    private String age;
    private String role;
    private String dob;
    private String gender;
    private String email;
    private String password;
}
