package com.example.bay01.employee;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping("/employee/{id}")
    public EmployeeResponse getEmployee(@PathVariable int id) {
        return new EmployeeResponse(1, "Somkiat Pui");
    }

}
