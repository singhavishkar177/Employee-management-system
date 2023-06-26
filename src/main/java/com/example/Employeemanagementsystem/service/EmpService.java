package com.example.Employeemanagementsystem.service;

import com.example.Employeemanagementsystem.payload.EmpDto;
import com.example.Employeemanagementsystem.payload.EmpResponse;

import java.util.List;

public interface EmpService {
    EmpResponse getAllEmployees(int pageNo, int pageSize,String sortBy,String sortDir);
    EmpDto getEmployeeById(int id);
    EmpDto createEmployee(EmpDto empDto);
    EmpDto updateEmployee(EmpDto empDto,int id);
    void deleteEmployee(int id);
}
