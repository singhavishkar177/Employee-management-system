package com.example.Employeemanagementsystem.controller;

import com.example.Employeemanagementsystem.payload.EmpDto;
import com.example.Employeemanagementsystem.payload.EmpResponse;
import com.example.Employeemanagementsystem.service.EmpService;
import com.example.Employeemanagementsystem.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@Tag(
        name = "CRUD rest api's for Employee resource"
)
public class EmpController {
    private EmpService service;

    public EmpController(EmpService service) {
    super();
        this.service = service;
    }
    @Operation(
            summary = "Get all Employee rest Api",
            description = "Get employee rest api is used to get all employee from  database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 ok"
    )
    @GetMapping
    public ResponseEntity<EmpResponse> getAllEmployees(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value="sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false)String sortDir){
        EmpResponse empDtoList = service.getAllEmployees(pageNo,pageSize,sortBy,sortDir);
        return ResponseEntity.ok(empDtoList);
    }
    @Operation(
            summary = "GET Employee by Id Api",
            description = "GET Employee by Id Api is used to get employee by its id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 ok"
    )
    @GetMapping("/{id}")
    public ResponseEntity<EmpDto> getEmployeeById(@PathVariable("id") int id){
        EmpDto empDto= service.getEmployeeById(id);
        return ResponseEntity.ok(empDto);
    }
    @Operation(
            summary = "Create POST Employee rest Api",
            description = "Create POST employee rest api is used to save employee in  database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 created"
    )
    @SecurityRequirement(           //this annotation is to provide authorization header in rest api in swagger ui
            name = "Bear Authentication"//here we have to pass security scheme name from securityconfig class
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<EmpDto> createEmp(@Valid @RequestBody EmpDto empDto){
        EmpDto empResponse = service.createEmployee(empDto);
        return new ResponseEntity<EmpDto>(empResponse, HttpStatus.CREATED);
    }
    @Operation(
            summary = "Update PUT Employee rest Api",
            description = "Update PUT employee rest api is used to update a perticular employee in  database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 created"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<EmpDto> updateEmp(@Valid @RequestBody EmpDto empDto,@PathVariable("id") int newId){
        EmpDto empResponse = service.updateEmployee(empDto,newId);
        return ResponseEntity.ok(empResponse);
    }
    @Operation(
            summary = "Delete Employee rest Api",
            description = "Delete employee rest api is used to delete employee from  database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 ok"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmp(@PathVariable("id") int newId){
    service.deleteEmployee(newId);
        return ResponseEntity.ok().body("employee deleted successfully");
    }
    @GetMapping("/organization/{orgId}")
    public ResponseEntity<List<EmpDto>> getAllEmpByOrgId(@PathVariable("orgId") int orgId){
        List<EmpDto> list = service.getAllEmpByOrgId(orgId);
        return ResponseEntity.ok(list);
    }
}
