package com.example.employeeapp.employeeapp.service;

import com.example.employeeapp.employeeapp.configurations.Constants;
import com.example.employeeapp.employeeapp.entity.Employee;
import com.example.employeeapp.employeeapp.model.EmployeeModel;
import com.example.employeeapp.employeeapp.repository.EmployeeRepository;
import com.example.employeeapp.employeeapp.transform.EmployeeTransform;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;


@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    public Employee createEmployee(EmployeeModel employeeModel) throws BadRequestException {
        validateEmail(employeeModel.getEmail());
        Employee employee = EmployeeTransform.toEmployee(new Employee(),employeeModel);
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) throws BadRequestException {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Employee not found with id " + id));

        return Optional.of(existingEmployee);
    }


    @Transactional
    public Employee updateEmployee(Long id, EmployeeModel employeeModel) throws BadRequestException {
        validateEmail(employeeModel.getEmail());
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
        Employee updatedEmployee = EmployeeTransform.toEmployee(existingEmployee,employeeModel);
        return employeeRepository.save(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
        employeeRepository.delete(existingEmployee);
    }

    private void validateEmail(String email) throws BadRequestException {
        Pattern pattern = Pattern.compile(Constants.EMAIL_REGEX);
        if (email != null && !pattern.matcher(email).matches()) {
            throw new BadRequestException("Invalid email format");
        }
    }

}
