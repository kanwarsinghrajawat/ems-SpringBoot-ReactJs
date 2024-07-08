package com.example.crud_spring;

import com.example.crud_spring.dto.EmployeeDataDto;
import com.example.crud_spring.entity.Employee;
import com.example.crud_spring.mapper.EmployeeMapper;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test

//    An EmployeeDataDto with specific values.
//    When: The createEmployee method is called.
//    Then: The saved EmployeeDataDto should match the given input values.

    public void testCreateEmployee() {
        // Given
        EmployeeDataDto employeeDataDto = new EmployeeDataDto();
        employeeDataDto.setName("John Doe");
        employeeDataDto.setAge("30");
        employeeDataDto.setDob("2000-01-01");
        employeeDataDto.setRole("Developer");
        employeeDataDto.setGender("Male");
        employeeDataDto.setEmail("john.doe@example.com");

        Employee employee = Employee.builder()
                .id(1L)
                .name("John Doe")
                .age("30")
                .dob("2000-01-01")
                .role("Developer")
                .gender("Male")
                .email("john.doe@example.com")
                .build();

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // When
        EmployeeDataDto savedEmployeeDto = employeeService.createEmployee(employeeDataDto);

        // Then
        assertEquals(employeeDataDto.getName(), savedEmployeeDto.getName());
        assertEquals(employeeDataDto.getAge(), savedEmployeeDto.getAge());
        assertEquals(employeeDataDto.getDob(), savedEmployeeDto.getDob());
        assertEquals(employeeDataDto.getRole(), savedEmployeeDto.getRole());
        assertEquals(employeeDataDto.getGender(), savedEmployeeDto.getGender());
        assertEquals(employeeDataDto.getEmail(), savedEmployeeDto.getEmail());
    }

    @Test

//    Given: An existing employee ID and an EmployeeDataDto with updated values.
//    When: The updateEmployee method is called.
//    Then: The updated EmployeeDataDto should match the given input values.
    public void testUpdateEmployee() {
        // Given
        Long employeeId = 1L;
        EmployeeDataDto updatedEmployeeDto = new EmployeeDataDto();
        updatedEmployeeDto.setName("Updated Name");
        updatedEmployeeDto.setAge("35");
        updatedEmployeeDto.setDob("1995-01-01");
        updatedEmployeeDto.setRole("Senior Developer");
        updatedEmployeeDto.setGender("Male");
        updatedEmployeeDto.setEmail("updated.name@example.com");

        Employee existingEmployee = Employee.builder()
                .id(1L)
                .name("Original Name")
                .age("30")
                .dob("2000-01-01")
                .role("Developer")
                .gender("Male")
                .email("john.doe@example.com")
                .build();

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        EmployeeDataDto updatedEmployeeResult = employeeService.updateEmployee(employeeId, updatedEmployeeDto);

        // Then
        assertEquals(updatedEmployeeDto.getName(), updatedEmployeeResult.getName());
        assertEquals(updatedEmployeeDto.getAge(), updatedEmployeeResult.getAge());
        assertEquals(updatedEmployeeDto.getDob(), updatedEmployeeResult.getDob());
        assertEquals(updatedEmployeeDto.getRole(), updatedEmployeeResult.getRole());
        assertEquals(updatedEmployeeDto.getGender(), updatedEmployeeResult.getGender());
        assertEquals(updatedEmployeeDto.getEmail(), updatedEmployeeResult.getEmail());
    }

    @Test

//    Given: A list of Employee entities.
//    When: The getAllEmployees method is called.
//    Then: The returned list of EmployeeDataDto should match the given employees' values.
    public void testGetAllEmployees() {
        // Given
        List<Employee> employees = new ArrayList<>();
        employees.add(Employee.builder()
                .id(1L)
                .name("John Doe")
                .age("30")
                .dob("2000-01-01")
                .role("Developer")
                .gender("Male")
                .email("john.doe@example.com")
                .build());
        employees.add(Employee.builder()
                .id(2L)
                .name("Jane Smith")
                .age("28")
                .dob("2002-02-02")
                .role("Designer")
                .gender("Female")
                .email("jane.smith@example.com")
                .build());

        when(employeeRepository.findAll()).thenReturn(employees);

        // When
        List<EmployeeDataDto> employeeDtos = employeeService.getAllEmployees();

        // Then
        assertEquals(employees.size(), employeeDtos.size());
        assertEquals(employees.get(0).getName(), employeeDtos.get(0).getName());
        assertEquals(employees.get(1).getName(), employeeDtos.get(1).getName());
    }

    @Test

//    Given: An existing employee ID.
//    When: The deleteEmployee method is called.
//    Then: The repository's deleteById method should be called once.

    public void testDeleteEmployee() {
        // Given
        Long employeeId = 1L;
        Employee employeeToDelete = Employee.builder()
                .id(1L)
                .name("John Doe")
                .age("30")
                .dob("2000-01-01")
                .role("Developer")
                .gender("Male")
                .email("john.doe@example.com")
                .build();

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employeeToDelete));

        employeeService.deleteEmployee(employeeId);

        verify(employeeRepository, times(1)).deleteById(employeeId);
    }

    @Test


//    Given: An email and password.
//    When: The checkEmployeeCredentials method is called.
//            Then: The returned EmployeeDataDto should match the given email and password.
    public void testCheckEmployeeCredentials() {
        String email = "john.doe@example.com";
        String password = "password";
        Employee employee = Employee.builder()
                .id(1L)
                .name("John Doe")
                .age("30")
                .dob("2000-01-01")
                .role("Developer")
                .gender("Male")
                .email("john.doe@example.com")
                .build();

        when(employeeRepository.findByEmailAndPassword(email, password)).thenReturn(employee);

        EmployeeDataDto foundEmployeeDto = employeeService.checkEmployeeCredentials(email, password);

        assertEquals(employee.getName(), foundEmployeeDto.getName());
        assertEquals(employee.getEmail(), foundEmployeeDto.getEmail());
    }
}
