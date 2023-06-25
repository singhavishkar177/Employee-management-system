package com.example.Employeemanagementsystem.controller;

import com.example.Employeemanagementsystem.payload.EmpDto;
import com.example.Employeemanagementsystem.service.EmpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmpController {
    private EmpService service;

    public EmpController(EmpService service) {
    super();
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EmpDto>> getAllEmployees(){
        List<EmpDto> empDtoList = service.getAllEmployees();
        return ResponseEntity.ok(empDtoList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmpDto> getEmployeeById(@PathVariable("id") int id){
        EmpDto empDto= service.getEmployeeById(id);
        return ResponseEntity.ok(empDto);
    }
    @PostMapping
    public ResponseEntity<EmpDto> createEmp(@RequestBody EmpDto empDto){
        EmpDto empResponse = service.createEmployee(empDto);
        return new ResponseEntity<EmpDto>(empResponse, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmpDto> updateEmp(@RequestBody EmpDto empDto,@PathVariable("id") int newId){
        EmpDto empResponse = service.updateEmployee(empDto,newId);
        return ResponseEntity.ok(empResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmp(@PathVariable("id") int newId){
    service.deleteEmployee(newId);
        return ResponseEntity.ok().body("employee deleted successfully");
    }
}
