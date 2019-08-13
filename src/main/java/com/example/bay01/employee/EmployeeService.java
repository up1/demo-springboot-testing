package com.example.bay01.employee;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    public EmployeeResponse getBy(int id) {
        return new EmployeeResponse(1, "Somkiat Pui");
    }

}
