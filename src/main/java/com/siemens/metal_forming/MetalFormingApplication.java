package com.siemens.metal_forming;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication @Slf4j
public class MetalFormingApplication {



	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MetalFormingApplication.class, args);
	}






}
