package com.example.Employeemanagementsystem.controller;

import com.example.Employeemanagementsystem.payload.OrgDto;
import com.example.Employeemanagementsystem.service.OrgService;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class OrgController {
    private OrgService orgService;

    public OrgController(OrgService orgService) {
        this.orgService = orgService;
    }
    //build add orgranization rest api
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<OrgDto> createOrganization(@RequestBody OrgDto orgDto){
            OrgDto orgResponse = orgService.createOrganization(orgDto);
            return new ResponseEntity<OrgDto>(orgResponse, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrgDto> getOrganizationById(@PathVariable(name = "id") int id){
        OrgDto orgDto = orgService.getOrganizationById(id);
        return ResponseEntity.ok(orgDto);
    }
    @GetMapping
    public ResponseEntity<List<OrgDto>> getAllOrganization(){
        List<OrgDto> list = orgService.getAllOrganization();
        return ResponseEntity.ok(list);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<OrgDto> updateOrganization(@RequestBody OrgDto orgDto,@PathVariable(name="id") int id){
        OrgDto orgResponse = orgService.updateOrganization(orgDto,id);
        return ResponseEntity.ok(orgResponse);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrganization(@PathVariable(name="id") int id){
        orgService.deleteOrganization(id);
        return ResponseEntity.ok("organization deleted successfully");
    }
}
