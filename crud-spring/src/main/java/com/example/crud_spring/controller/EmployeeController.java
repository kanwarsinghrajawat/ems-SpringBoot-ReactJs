package com.example.crud_spring.controller;


import com.example.crud_spring.dto.EmployeeDataDto;
import com.example.crud_spring.service.EmployeeService;
import com.example.crud_spring.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@CrossOrigin  // resolve cross issue
@RestController //restfull controller it handles http request
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired   // allow the controller to use methods defined in employee service
    private EmployeeService employeeService;



//
//    @PostMapping
//    public ResponseEntity<EmployeeDataDto> createEmployee(@RequestBody EmployeeDataDto employeeDataDto) {
//        EmployeeDataDto savedEmployee = employeeService.createEmployee(employeeDataDto);
//        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
//    }



    @PostMapping
    public ResponseEntity<EmployeeDataDto> createEmployee(@RequestBody EmployeeDataDto employeeDataDto) {
        // Hash the password using SHA-256
        String hashedPassword = HashUtil.sha256Hash(employeeDataDto.getPassword());
        employeeDataDto.setPassword(hashedPassword);
        EmployeeDataDto savedEmployee = employeeService.createEmployee(employeeDataDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDataDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDataDto updatedEmployee) {
        String hashedPassword = HashUtil.sha256Hash(updatedEmployee.getPassword());
        updatedEmployee.setPassword(hashedPassword);
        EmployeeDataDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);
        return ResponseEntity.ok(employeeDto);
    }




//    get all records
    @GetMapping
    public ResponseEntity<List<EmployeeDataDto>> getAllEmployees(){
       List<EmployeeDataDto> employees =  employeeService.getAllEmployees();
       return  ResponseEntity.ok(employees);
    }



    //delete api

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("deleted successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<EmployeeDataDto> checkEmployeeCredentials(@RequestBody EmployeeDataDto employeeDataDto) {
        String hashedPassword = HashUtil.sha256Hash(employeeDataDto.getPassword());
        EmployeeDataDto verifiedEmployee = employeeService.checkEmployeeCredentials(employeeDataDto.getEmail(), hashedPassword);
        if (verifiedEmployee != null) {
            // Create a new EmployeeDataDto with only role and name
            EmployeeDataDto responseDto = new EmployeeDataDto();
            responseDto.setRole(verifiedEmployee.getRole());
            responseDto.setName(verifiedEmployee.getName());

            return ResponseEntity.ok(responseDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

