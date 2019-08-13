package com.example.bay01.employee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void success_get_employee_by_id_1() {
        // Arrange
        this.entityManager.persist(new Employee("Somkiat", "Pui"));

        // Act
        Optional<Employee> employee = employeeRepository.findById(1);

        // Assert
        assertEquals("Somkiat", employee.get().getFirstName());
        assertEquals("Pui", employee.get().getLastName());
    }

}