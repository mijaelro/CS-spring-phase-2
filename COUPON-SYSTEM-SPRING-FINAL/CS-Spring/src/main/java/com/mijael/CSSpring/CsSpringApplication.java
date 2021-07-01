package com.mijael.CSSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CsSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsSpringApplication.class, args);
		System.out.println("IOContainer was loaded");
	}

}
