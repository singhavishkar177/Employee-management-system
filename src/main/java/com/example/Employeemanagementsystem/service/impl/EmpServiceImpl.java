package com.example.Employeemanagementsystem.service.impl;

import com.example.Employeemanagementsystem.Model.Employee;
import com.example.Employeemanagementsystem.Model.Organization;
import com.example.Employeemanagementsystem.exception.ResourceNotFoundException;
import com.example.Employeemanagementsystem.payload.EmpDto;
import com.example.Employeemanagementsystem.payload.EmpResponse;
import com.example.Employeemanagementsystem.repository.EmpRepository;
import com.example.Employeemanagementsystem.repository.OrganizationRepository;
import com.example.Employeemanagementsystem.service.EmpService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpServiceImpl implements EmpService {

   private  ModelMapper modelMapper;
    private EmpRepository repository;
    private OrganizationRepository orgRepository;
    public EmpServiceImpl(ModelMapper modelMapper,EmpRepository repository,
                          OrganizationRepository orgRepository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.orgRepository = orgRepository;
    }

    @Override
    public EmpResponse getAllEmployees(int pageNo,int pageSize,String sortBy,String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                 : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        Page<Employee> empList = repository.findAll(pageable);
        List<Employee> empDtoList = empList.getContent();
        List<EmpDto> content =  empDtoList.stream().map(obj -> mapToDto(obj)).collect(Collectors.toList());
        EmpResponse empResponse = new EmpResponse();
        empResponse.setContent(content);
        empResponse.setPageNo(empList.getNumber());
        empResponse.setPageSize(empList.getSize());
        empResponse.setTotalElements(empList.getTotalElements());
        empResponse.setTotalPages(empList.getTotalPages());
        empResponse.setLast(empList.isLast());
        return empResponse;
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
        Organization organization = orgRepository.findById(empDto.getOrganizationId())
                .orElseThrow(() -> new ResourceNotFoundException("organization","id", empDto.getOrganizationId()));
        emp.setOrganization(organization);
        Employee createEmp = repository.save(emp);
        EmpDto empCreateDto = mapToDto(createEmp);
        return empCreateDto;
    }

    @Override
    public EmpDto updateEmployee(EmpDto empDto, int id) {
        Employee emp1 = mapToEntity(empDto);
        Organization organization = orgRepository.findById(empDto.getOrganizationId())
                .orElseThrow(() -> new ResourceNotFoundException("organization","id",id));
        emp1 = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","id",id));
        emp1.setName(empDto.getName());
        emp1.setTitle(empDto.getTitle());
        emp1.setProject(empDto.getProject());
        emp1.setOrganization(organization);
        Employee updateEmp = repository.save(emp1);
        EmpDto empResponse = mapToDto(emp1);
        return empResponse;
    }

    @Override
    public void deleteEmployee(int id) {
        Employee emp = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","id",id));
        repository.delete(emp);
    }

    @Override
    public List<EmpDto> getAllEmpByOrgId(int orgId) {
        Organization organization = orgRepository.findById(orgId)
                .orElseThrow(()-> new ResourceNotFoundException("organization","id",orgId));
        List<Employee> list = repository.findByOrganizationId(orgId);
        return list.stream().map(obj -> mapToDto(obj)).collect(Collectors.toList());
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
