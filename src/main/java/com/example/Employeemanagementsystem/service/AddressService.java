package com.example.Employeemanagementsystem.service;

import com.example.Employeemanagementsystem.payload.AddressDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AddressService {
    List<AddressDto> getAddressByEmpId(int empId);
    AddressDto getAddressById(int addressId,int empId);
    AddressDto createAddress(AddressDto addressDto,int empId);
    AddressDto updateAddress(AddressDto addressDto,int empId,int addressId);
    void deleteAddress(int addressId,int empId);
}
