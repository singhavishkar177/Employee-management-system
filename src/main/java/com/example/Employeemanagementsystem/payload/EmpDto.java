package com.example.Employeemanagementsystem.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpDto {

    private int id;
    @NotEmpty
    @Size(min = 2,message = "Name should have atleast 2 characters")
    private String name;
    @NotEmpty
    @Size(min = 2,message = "title should have atleast 2 characters")
    private String title;
    @NotEmpty
    private String project;
    private Set<AddressDto> addressDtos;

    private int organizationId;
}
