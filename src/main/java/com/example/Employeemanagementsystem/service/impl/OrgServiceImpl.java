package com.example.Employeemanagementsystem.service.impl;

import com.example.Employeemanagementsystem.Model.Organization;
import com.example.Employeemanagementsystem.exception.ResourceNotFoundException;
import com.example.Employeemanagementsystem.payload.OrgDto;
import com.example.Employeemanagementsystem.repository.OrganizationRepository;
import com.example.Employeemanagementsystem.service.OrgService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrgServiceImpl implements OrgService {

   private ModelMapper modelMapper;
   private OrganizationRepository orgRepository;

    public OrgServiceImpl(ModelMapper modelMapper, OrganizationRepository orgRepository) {
        this.modelMapper = modelMapper;
        this.orgRepository = orgRepository;
    }

    @Override
    public OrgDto createOrganization(OrgDto orgDto) {
        Organization organization = mapToEntity(orgDto);
        return mapToDto(orgRepository.save(organization));
    }

    @Override
    public OrgDto getOrganizationById(int id) {
        Organization organization = orgRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("organization","id",id));
        return mapToDto(organization);
    }

    @Override
    public List<OrgDto> getAllOrganization() {
        List<Organization> list = orgRepository.findAll();
        return list.stream().map(obj -> mapToDto(obj)).collect(Collectors.toList());
    }

    @Override
    public OrgDto updateOrganization(OrgDto orgDto, int id) {
        Organization organization = orgRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("organization","id",id));
        organization.setOrgName(orgDto.getOrgName());
        orgRepository.save(organization);
        return mapToDto(organization);
    }

    @Override
    public void deleteOrganization(int id) {
        Organization organization = orgRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("organization","id",id));
        orgRepository.delete(organization);
    }

    public OrgDto mapToDto(Organization organization){
        OrgDto orgDto = modelMapper.map(organization, OrgDto.class);
        return orgDto;
    }
    public Organization mapToEntity(OrgDto orgDto){
        Organization organization = modelMapper.map(orgDto,Organization.class);
        return organization;
    }
}
