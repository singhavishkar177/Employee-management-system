package com.example.Employeemanagementsystem.repository;

import com.example.Employeemanagementsystem.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Employee,Integer> {
//new code
}
