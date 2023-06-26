package com.example.Employeemanagementsystem.controller;

import com.example.Employeemanagementsystem.payload.AddressDto;
import com.example.Employeemanagementsystem.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AddressController {
private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    @GetMapping("/employees/{empId}/address")
    public ResponseEntity<List<AddressDto>> getAddressByEmpId(@PathVariable("empId") int empId){
        List<AddressDto> addressDtoList = addressService.getAddressByEmpId(empId);
        return ResponseEntity.ok().body(addressDtoList);
    }
    @GetMapping("/employees/{empId}/address/{addressId}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable("empId") int empId,
                                                     @PathVariable("addressId") int addressId){
        AddressDto addressDto = addressService.getAddressById(addressId,empId);
        return ResponseEntity.ok().body(addressDto);
    }
    @PostMapping("/employees/{empId}/address")
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto,
                                                    @PathVariable("empId") int empId){
        AddressDto addressDto1 = addressService.createAddress(addressDto,empId);
        return ResponseEntity.ok().body(addressDto1);
    }
    @PutMapping("/employees/{empId}/address/{addressId}")
    public ResponseEntity<AddressDto> updateAddress(@RequestBody AddressDto addressDto,
                                                    @PathVariable("empId") int empId,
                                                    @PathVariable("addressId") int addressId){
        AddressDto addressDto1 = addressService.updateAddress(addressDto,empId,addressId);
        return new ResponseEntity<>(addressDto1,HttpStatus.CREATED);
    }
    @DeleteMapping("/employees/{empId}/address/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable("empId") int empId,
                                               @PathVariable("addressId") int addressId){
        addressService.deleteAddress(addressId,empId);
        return ResponseEntity.ok().body("address deleted successfully");
    }
}
