package com.example.Employeemanagementsystem.controller;

import com.example.Employeemanagementsystem.payload.EmpDto;
import com.example.Employeemanagementsystem.payload.EmpResponse;
import com.example.Employeemanagementsystem.service.EmpService;
import com.example.Employeemanagementsystem.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<EmpResponse> getAllEmployees(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value="sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false)String sortDir){
        EmpResponse empDtoList = service.getAllEmployees(pageNo,pageSize,sortBy,sortDir);
        return ResponseEntity.ok(empDtoList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmpDto> getEmployeeById(@PathVariable("id") int id){
        EmpDto empDto= service.getEmployeeById(id);
        return ResponseEntity.ok(empDto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EmpDto> createEmp(@RequestBody EmpDto empDto){
        EmpDto empResponse = service.createEmployee(empDto);
        return new ResponseEntity<EmpDto>(empResponse, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<EmpDto> updateEmp(@RequestBody EmpDto empDto,@PathVariable("id") int newId){
        EmpDto empResponse = service.updateEmployee(empDto,newId);
        return ResponseEntity.ok(empResponse);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmp(@PathVariable("id") int newId){
    service.deleteEmployee(newId);
        return ResponseEntity.ok().body("employee deleted successfully");
    }
}
