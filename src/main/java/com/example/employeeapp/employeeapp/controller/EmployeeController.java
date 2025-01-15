package com.example.employeeapp.employeeapp.controller;

import com.example.employeeapp.employeeapp.configurations.Constants;
import com.example.employeeapp.employeeapp.entity.Employee;
import com.example.employeeapp.employeeapp.model.EmployeeModel;
import com.example.employeeapp.employeeapp.service.EmployeeService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = Constants.BASIC_ROUTE)
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/create/new-employee")
    public Employee createEmployee(@Valid @RequestBody EmployeeModel employeeModel) throws BadRequestException {
        return employeeService.createEmployee(employeeModel);
    }

    @GetMapping("list/all/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("list/employee/{employeeId}")
    public Optional<Employee> getEmployeeById(@PathVariable Long employeeId) throws BadRequestException {
        return employeeService.getEmployeeById(employeeId);
    }

    @PutMapping("update/{id}")
    public Employee updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeModel employeeModel) throws BadRequestException {
        return employeeService.updateEmployee(id, employeeModel);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
