package com.example.crud_spring.service;

import com.example.crud_spring.dto.EmployeeDataDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeService {

    EmployeeDataDto createEmployee(EmployeeDataDto employeeDataDto);

    List<EmployeeDataDto> getAllEmployees();

    EmployeeDataDto updateEmployee(Long employeeId, EmployeeDataDto updatedEmployee);

    void deleteEmployee(Long employeeId);

    EmployeeDataDto checkEmployeeCredentials(String id, String password);
}
