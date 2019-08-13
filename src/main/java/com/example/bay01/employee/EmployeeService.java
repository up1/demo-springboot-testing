package com.example.bay01.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeResponse getBy(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return new EmployeeResponse(1, String.format("%s %s", employee.get().getFirstName(), employee.get().getLastName()));
    }

}
