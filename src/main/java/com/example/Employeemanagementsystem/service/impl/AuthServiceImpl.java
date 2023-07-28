package com.example.Employeemanagementsystem.service.impl;

import com.example.Employeemanagementsystem.Model.Role;
import com.example.Employeemanagementsystem.Model.User;
import com.example.Employeemanagementsystem.Security.JwtTokenProvider;
import com.example.Employeemanagementsystem.exception.BlogApiException;
import com.example.Employeemanagementsystem.payload.LoginDto;
import com.example.Employeemanagementsystem.payload.RegisterDto;
import com.example.Employeemanagementsystem.repository.RoleRepository;
import com.example.Employeemanagementsystem.repository.UserRepository;
import com.example.Employeemanagementsystem.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    public AuthServiceImpl(AuthenticationManager authenticationManager,UserRepository userRepository,
                           RoleRepository roleRepository,PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) throws ParseException {
        Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {
        //add check if username already existes in database
        if(userRepository.existsByUsername(registerDto.getUsername()))
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Username already exists");
        //add check if email already existes in database
        if(userRepository.existsByEmail(registerDto.getEmail()))
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Email already exists");
        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return "User registered successfully";
    }
}
