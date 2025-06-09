package com.springboot.bakefinity;

import com.springboot.bakefinity.model.dtos.ProductDTO;
import com.springboot.bakefinity.model.entities.Category;
import com.springboot.bakefinity.repositories.interfaces.ProductRepo;
import com.springboot.bakefinity.repositories.interfaces.UserRepo;
import com.springboot.bakefinity.services.interfaces.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class BakefinityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BakefinityApplication.class, args);
	}


	@Bean
	CommandLineRunner applicationInitializer(ProductService productService, UserRepo userRepo) {
		return (args) -> {
			System.out.println(productService.getAllProducts());
			System.out.println(userRepo.findByEmail("sara.ali@example.com").get());
		};
	}

}
