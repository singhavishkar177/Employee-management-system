package com.example.Employeemanagementsystem.service.impl;

import com.example.Employeemanagementsystem.Model.Employee;
import com.example.Employeemanagementsystem.exception.ResourceNotFoundException;
import com.example.Employeemanagementsystem.payload.EmpDto;
import com.example.Employeemanagementsystem.repository.EmpRepository;
import com.example.Employeemanagementsystem.service.EmpService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpServiceImpl implements EmpService {

   private  ModelMapper modelMapper;
    private EmpRepository repository;
    public EmpServiceImpl(ModelMapper modelMapper,EmpRepository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public List<EmpDto> getAllEmployees() {
        List<Employee> empList = repository.findAll();
        List<EmpDto> empDtoList = empList.stream().map(obj -> mapToDto(obj)).collect(Collectors.toList());
        return empDtoList;
    }

    @Override
    public EmpDto getEmployeeById(int id) {
        Employee emp = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","id",id));
        EmpDto empDto = mapToDto(emp);
        return empDto;
    }

    @Override
    public EmpDto createEmployee(EmpDto empDto) {
        Employee emp = mapToEntity(empDto);
        Employee createEmp = repository.save(emp);
        EmpDto empCreateDto = mapToDto(createEmp);
        return empCreateDto;
    }

    @Override
    public EmpDto updateEmployee(EmpDto empDto, int id) {
        Employee emp1 = mapToEntity(empDto);
        emp1 = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","id",id));
        emp1.setName(empDto.getName());
        emp1.setTitle(empDto.getTitle());
        emp1.setProject(empDto.getProject());
        Employee updateEmp = repository.save(emp1);
        EmpDto empResponse = mapToDto(emp1);
        return empResponse;
    }

    @Override
    public void deleteEmployee(int id) {
        Employee emp = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","id",id));
        repository.delete(emp);
    }

    public EmpDto mapToDto(Employee emp){
    EmpDto empDto = modelMapper.map(emp, EmpDto.class);
    return empDto;
    }
    public Employee mapToEntity(EmpDto empDto){
        Employee emp = modelMapper.map(empDto,Employee.class);
        return emp;
    }
}
