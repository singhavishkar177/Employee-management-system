package com.example.Employeemanagementsystem.service;

import com.example.Employeemanagementsystem.payload.OrgDto;

import java.util.List;

public interface OrgService {
    OrgDto createOrganization(OrgDto orgDto);
    OrgDto getOrganizationById(int id);
    List<OrgDto> getAllOrganization();
    OrgDto updateOrganization(OrgDto orgDto,int id);
    void deleteOrganization(int id);
}
