package com.dsd.rfoodsp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RFoodSpApplication {

	public static void main(String[] args) {
		SpringApplication.run(RFoodSpApplication.class, args);
		System.out.println("ENGAGE");
	}
	

	// bean para Mapper, para no crear tantas instancias
	@Bean
	public ModelMapper modelMap(){
		return new ModelMapper();
	}

}
