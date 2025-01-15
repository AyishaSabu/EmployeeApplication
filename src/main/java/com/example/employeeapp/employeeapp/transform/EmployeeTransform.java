package com.example.employeeapp.employeeapp.transform;

import com.example.employeeapp.employeeapp.entity.Employee;
import com.example.employeeapp.employeeapp.model.EmployeeModel;

import java.util.Optional;

public class EmployeeTransform {

    public static Employee toEmployee(Employee employee,EmployeeModel employeeModel) {
        Optional.ofNullable(employeeModel.getEmail()).ifPresent(employee::setEmail);
        Optional.ofNullable(employeeModel.getName()).ifPresent(employee::setName);
        Optional.ofNullable(employeeModel.getDepartment()).ifPresent(employee::setDepartment);
        Optional.ofNullable(employeeModel.getSalary()).ifPresent(employee::setSalary);
        return employee;
    }
}
