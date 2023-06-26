package com.example.Employeemanagementsystem.repository;

import com.example.Employeemanagementsystem.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    List<Address> findByEmployeeId(int id);
}
