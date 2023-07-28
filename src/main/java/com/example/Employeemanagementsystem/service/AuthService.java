package com.example.Employeemanagementsystem.service;

import com.example.Employeemanagementsystem.payload.LoginDto;
import com.example.Employeemanagementsystem.payload.RegisterDto;

import java.text.ParseException;

public interface AuthService {
    String login(LoginDto loginDto) throws ParseException;
    String register(RegisterDto registerDto);

}
