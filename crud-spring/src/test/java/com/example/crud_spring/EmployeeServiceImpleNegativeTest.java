package com.example.crud_spring;

import com.example.crud_spring.dto.EmployeeDataDto;
import com.example.crud_spring.entity.Employee;
import com.example.crud_spring.repository.EmployeeRepository;
import com.example.crud_spring.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmployeeServiceImpleNegativeTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
//    Given: An EmployeeDataDto with specific values.
//    When: The createEmployee method is called and the repository throws a RuntimeException.
//    Then: The method should throw the same exception with the message "Database error".
    public void testCreateEmployee_Negative() {
        // Given
        EmployeeDataDto employeeDataDto = new EmployeeDataDto();
        employeeDataDto.setName("John Doe");
        employeeDataDto.setAge("30");
        employeeDataDto.setDob("2000-01-01");
        employeeDataDto.setRole("Developer");
        employeeDataDto.setGender("Male");
        employeeDataDto.setEmail("john.doe@example.com");

        when(employeeRepository.save(any(Employee.class))).thenThrow(new RuntimeException("Database error"));

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            employeeService.createEmployee(employeeDataDto);
        });
        assertEquals("Database error", exception.getMessage());
    }

    @Test
//    Given: An employee ID that does not exist.
//    When: The updateEmployee method is called.
//    Then: The method should throw a RuntimeException with the message "No value present".

    public void testUpdateEmployee_Negative() {
        // Given
        Long employeeId = 1L;
        EmployeeDataDto updatedEmployeeDto = new EmployeeDataDto();
        updatedEmployeeDto.setName("Updated Name");
        updatedEmployeeDto.setAge("35");
        updatedEmployeeDto.setDob("1995-01-01");
        updatedEmployeeDto.setRole("Senior Developer");
        updatedEmployeeDto.setGender("Male");
        updatedEmployeeDto.setEmail("updated.name@example.com");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            employeeService.updateEmployee(employeeId, updatedEmployeeDto);
        });
        assertEquals("No value present", exception.getMessage());
    }

    @Test
//    Given: The repository throws a RuntimeException when fetching all employees.
//    When: The getAllEmployees method is called.
//    Then: The method should throw the same exception with the message "Database error".
    public void testGetAllEmployees_Negative() {
        // Given
        when(employeeRepository.findAll()).thenThrow(new RuntimeException("Database error"));
        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            employeeService.getAllEmployees();
        });
        assertEquals("Database error", exception.getMessage());
    }

    @Test
//    Given: An employee ID that does not exist.
//      When: The deleteEmployee method is called.
//       Then: The method should throw a RuntimeException with the message "No value present".

    public void testDeleteEmployee_Negative() {
        // Given
        Long employeeId = 1L;

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            employeeService.deleteEmployee(employeeId);
        });
        assertEquals("No value present", exception.getMessage());
    }

//    @Test
    public void testCheckEmployeeCredentials_Negative() {
        String email = "john.doe@example.com";
        String password = "password";

        when(employeeRepository.findByEmailAndPassword(email, password)).thenReturn(null);

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            employeeService.checkEmployeeCredentials(email, password);
        });
        assertEquals("Invalid credentials", exception.getMessage());
    }
}
