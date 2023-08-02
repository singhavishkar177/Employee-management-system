package com.example.Employeemanagementsystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "org_name",nullable = false)
    private String orgName;
    @OneToMany(mappedBy = "organization",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Employee> employees ;

}
