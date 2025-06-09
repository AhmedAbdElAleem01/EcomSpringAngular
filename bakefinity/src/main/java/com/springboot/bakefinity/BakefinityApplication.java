package com.springboot.bakefinity;

import com.springboot.bakefinity.repositories.interfaces.UserRepo;
import com.springboot.bakefinity.services.interfaces.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BakefinityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BakefinityApplication.class, args);
	}


	@Bean
	CommandLineRunner applicationInitializer(ProductService productService, UserRepo userRepo) {
		return (args) -> {
			System.out.println(productService.getAllProducts());
		};
	}

}
