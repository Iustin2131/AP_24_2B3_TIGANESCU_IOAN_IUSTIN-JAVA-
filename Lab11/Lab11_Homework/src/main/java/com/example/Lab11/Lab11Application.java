package com.example.Lab11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebMvc
public class Lab11Application implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Lab11Application.class, args);
	}
}
