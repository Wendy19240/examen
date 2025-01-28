package com.example.asigantura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AsiganturaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsiganturaApplication.class, args);
	}

}
