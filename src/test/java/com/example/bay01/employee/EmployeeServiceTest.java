package com.example.bay01.employee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService = new EmployeeService();

    @Test
    public void success_with_get_by_id_1() {
        // Arrange
        given(this.employeeRepository.findById(1))
                .willReturn(Optional.of(new Employee("Somkiat", "Pui")));

        employeeService.setEmployeeRepository(employeeRepository);

        // Act
        EmployeeResponse response = employeeService.getBy(1);

        // Assert
        assertEquals(1, response.getId());
        assertEquals("Somkiat Pui", response.getName());
    }

}