package com.example.Employeemanagementsystem.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgDto {
    private int id;
    @NotEmpty
    @Size(min = 2,message = "Name should have atleast 2 characters")
    private String orgName;
}
