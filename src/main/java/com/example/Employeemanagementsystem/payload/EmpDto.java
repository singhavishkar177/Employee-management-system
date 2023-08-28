package com.example.Employeemanagementsystem.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "EmpDto model Information"
)
public class EmpDto {

    private int id;
    @Schema(
            description = "Employee name"
    )
    @NotEmpty
    @Size(min = 2,message = "Name should have atleast 2 characters")
    private String name;
    @Schema(
            description = "Employee title"
    )
    @NotEmpty
    @Size(min = 2,message = "title should have atleast 2 characters")
    private String title;
    @Schema(
            description = "Employee project"
    )
    @NotEmpty
    private String project;
    private Set<AddressDto> addressDtos;
    @Schema(
            description = "Employee's organization id"
    )
    private int organizationId;
}
