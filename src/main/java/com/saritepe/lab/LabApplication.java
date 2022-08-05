package com.saritepe.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LabApplication {

	public static void main(String[] args) {

/*
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "user";
		String encodedPassword = encoder.encode(rawPassword);

		System.out.println("---");
		System.out.println(encodedPassword);
		System.out.println("---");

 */
		SpringApplication.run(LabApplication.class, args);
	}

}
