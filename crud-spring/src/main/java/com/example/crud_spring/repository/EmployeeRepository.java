package com.example.crud_spring.repository;

import com.example.crud_spring.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


//through this class we can perform all crud on database
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmailAndPassword(String email, String password);

}


