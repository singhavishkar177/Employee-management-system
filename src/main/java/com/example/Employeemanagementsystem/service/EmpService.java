package com.example.Employeemanagementsystem.service;

import com.example.Employeemanagementsystem.payload.EmpDto;

import java.util.List;

public interface EmpService {
    List<EmpDto> getAllEmployees();
    EmpDto getEmployeeById(int id);
    EmpDto createEmployee(EmpDto empDto);
    EmpDto updateEmployee(EmpDto empDto,int id);
    void deleteEmployee(int id);
}
