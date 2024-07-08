package com.example.crud_spring.mapper;

import com.example.crud_spring.dto.EmployeeDataDto;
import com.example.crud_spring.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDataDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDataDto(
                employee.getId(),
                employee.getName(),
                employee.getAge(),
                employee.getRole(),
                employee.getDob(),
                employee.getGender(),
                employee.getEmail(),
                employee.getPassword()
        );
    }

    public static Employee mapToEmployee(EmployeeDataDto employeeDto) {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getName(),
                employeeDto.getAge(),
                employeeDto.getRole(),
                employeeDto.getDob(),
                employeeDto.getGender(),
                employeeDto.getEmail(),
                employeeDto.getPassword()
        );
    }
}
