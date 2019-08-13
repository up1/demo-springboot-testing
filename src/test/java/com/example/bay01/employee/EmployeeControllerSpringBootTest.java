package com.example.bay01.employee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.springframework.boot.test.context.SpringBootTest.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerSpringBootTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void success_getEmployee_id_1() {
        // Act
        EmployeeResponse response = restTemplate.getForObject("/employee/1", EmployeeResponse.class);

        // Assert
        assertEquals(1, response.getId());
        assertEquals("Somkiat Pui", response.getName());
    }
}