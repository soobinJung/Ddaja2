package com.bng.ddaja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DdajaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DdajaApplication.class, args);
	}
}
