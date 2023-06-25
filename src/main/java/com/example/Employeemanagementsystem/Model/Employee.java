package com.example.Employeemanagementsystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee",uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "project",nullable = false)
    private String project;
}
