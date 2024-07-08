package com.example.crud_spring.service;

import com.example.crud_spring.dto.EmployeeDataDto;
import com.example.crud_spring.entity.Employee;
import com.example.crud_spring.mapper.EmployeeMapper;
import com.example.crud_spring.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;


    @Override
    public EmployeeDataDto createEmployee(EmployeeDataDto employeeDataDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDataDto);

        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDataDto updateEmployee(Long employeeId, EmployeeDataDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        employee.setName(updatedEmployee.getName());
        employee.setAge(updatedEmployee.getAge());
        employee.setDob(updatedEmployee.getDob());
        employee.setRole(updatedEmployee.getRole());
        employee.setGender(updatedEmployee.getGender());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public List<EmployeeDataDto> getAllEmployees() {
       List<Employee> employees =  employeeRepository.findAll();
        return employees.stream().map((employee)-> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }



    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        employeeRepository.deleteById(employeeId);

    }

    @Override
    public EmployeeDataDto checkEmployeeCredentials(String email, String password) {
        Employee employee = employeeRepository.findByEmailAndPassword(email, password);
        if (employee != null) {
            return EmployeeMapper.mapToEmployeeDto(employee);
        }
        return null;
    }

}
