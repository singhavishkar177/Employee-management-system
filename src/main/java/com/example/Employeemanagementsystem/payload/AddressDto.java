package com.example.Employeemanagementsystem.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private int id;
    @NotEmpty(message = "Locality should not be empty or null")
    private String locality;
    @NotEmpty(message = "City should not be empty or null")
    private String city;
    @NotEmpty(message = "State should not be empty or null")
    private String state;
}
