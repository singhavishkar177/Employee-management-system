package com.example.Employeemanagementsystem;

import com.example.Employeemanagementsystem.Model.Role;
import com.example.Employeemanagementsystem.repository.RoleRepository;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(
				title = "Spring boot Employee management System API's",
				description = "Spring boot Employee management System API's Documentation",
				version="v1.0",
				contact = @Contact(
						name = "Rohit",
						email = "xyz@gmail.com",
						url = "www.google.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "www.google.com/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				 description = "Spring Boot Employee management System Documentation",
				url = "https://github.com/singhavishkar177/Employee-management-system/tree/develop"
		)
)
public class EmployeeManagementSystemApplication { //implements CommandLineRunner {		//Command LineRunner provides run method which we will use to load properties at app start up

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}

//	@Autowired
//	RoleRepository roleRepository;
//	@Override
//	public void run(String... args) throws Exception {		// we were manually putting user and admin role in role table in db wewant to enter these at app startup from here
//		Role adminRole = new Role();
//		adminRole.setName("ROLE_ADMIN");
//		roleRepository.save(adminRole);
//
//		Role userRole = new Role();
//		userRole.setName("ROLE_USER");
//		roleRepository.save(userRole);
//	}
}
