package com.auth.detailes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.auth.detailes.business.repositories.auth.UserRepository;

@SpringBootApplication
public class GestionPatrimoineApplication {

	@Autowired
	UserRepository userRepository; 
	
	public static void main(String[] args) {
		SpringApplication.run(GestionPatrimoineApplication.class, args);	}

	
}
