package com.example.Employeemanagementsystem.repository;

import com.example.Employeemanagementsystem.Model.Employee;
import com.example.Employeemanagementsystem.Model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpRepository extends JpaRepository<Employee,Integer> {
        List<Employee> findByOrganizationId(int orgId);
}
