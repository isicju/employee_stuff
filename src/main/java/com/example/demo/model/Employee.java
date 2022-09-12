package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private String hireDate;
    private String email;
    private Integer salary;
}
