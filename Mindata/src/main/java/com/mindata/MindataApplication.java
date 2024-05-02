package com.mindata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MindataApplication {

	public static void main(String[] args) {
		SpringApplication.run(MindataApplication.class, args);
	}

}
