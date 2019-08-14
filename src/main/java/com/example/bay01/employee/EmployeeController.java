package com.example.bay01.employee;

import com.example.bay01.post.PostGateway;
import com.example.bay01.post.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PostGateway postGateway;

    @GetMapping("/employee/{id}")
    public EmployeeResponse getEmployee(@PathVariable int id) {
        return employeeService.getBy(id);
    }

    @GetMapping("/post/{id}")
    public Optional<PostResponse> getPost(@PathVariable int id) {
        return postGateway.getPostBy(id);
    }

}
