package com.example.Employeemanagementsystem.service.impl;

import com.example.Employeemanagementsystem.Model.Address;
import com.example.Employeemanagementsystem.Model.Employee;
import com.example.Employeemanagementsystem.exception.BlogApiException;
import com.example.Employeemanagementsystem.exception.ResourceNotFoundException;
import com.example.Employeemanagementsystem.payload.AddressDto;
import com.example.Employeemanagementsystem.repository.AddressRepository;
import com.example.Employeemanagementsystem.repository.EmpRepository;
import com.example.Employeemanagementsystem.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AddressServiceImpl implements AddressService {

    private ModelMapper modelMapper;
    private AddressRepository addressRepository;
    private EmpRepository empRepository;
    public AddressServiceImpl(ModelMapper modelMapper, AddressRepository addressRepository,
                              EmpRepository empRepository) {
        this.modelMapper = modelMapper;
        this.addressRepository = addressRepository;
        this.empRepository = empRepository;
    }

    @Override
    public List<AddressDto> getAddressByEmpId(int empId) {
        List<Address> addressList = addressRepository.findByEmployeeId(empId);
        return addressList.stream().map(obj -> mapToDto(obj)).collect(Collectors.toList());
    }

    @Override
    public AddressDto getAddressById(int addressId, int empId) {
        Employee employee = empRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee","id",empId));
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address","id",addressId));
        if(!(address.getEmployee().getId()==employee.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Address does not belong to employee");
        }
        return mapToDto(address);
    }

    @Override
    public AddressDto createAddress(AddressDto addressDto, int empId) {
        Address address = mapToEntity(addressDto);
        Employee employee =empRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee","id",empId));
        address.setEmployee(employee);
        Address newAddress = addressRepository.save(address);
        return mapToDto(newAddress);
    }

    @Override
    public AddressDto updateAddress(AddressDto addressDto, int empId, int addressId) {
       Employee employee = empRepository.findById(empId)
               .orElseThrow(() -> new ResourceNotFoundException("Employee","id",empId));
       Address address = addressRepository.findById(addressId)
               .orElseThrow(() ->new  ResourceNotFoundException("Address","id",addressId));
        if(!(address.getEmployee().getId()==employee.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Address does not belong to employee");
        }
        address.setCity(addressDto.getCity());
        address.setLocality(addressDto.getLocality());
        address.setState(addressDto.getState());
        Address newAddress = addressRepository.save(address);
        return mapToDto(newAddress);
    }

    @Override
    public void deleteAddress(int addressId, int empId) {
        Employee employee = empRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee","id",empId));
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() ->new  ResourceNotFoundException("Address","id",addressId));
        if(!(address.getEmployee().getId()==employee.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Address does not belong to employee");
        }
        addressRepository.delete(address);
    }
    public AddressDto mapToDto(Address address){
        AddressDto addressDto = modelMapper.map(address, AddressDto.class);
        return addressDto;
    }
    public Address mapToEntity(AddressDto addressDto){
        Address address = modelMapper.map(addressDto,Address.class);
        return address;
    }
}
