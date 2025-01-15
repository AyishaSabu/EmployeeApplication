package com.example.employeeapp.employeeapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeeModel {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "department")
    private String department;

    @JsonProperty(value = "salary")
    private BigDecimal salary;
}
