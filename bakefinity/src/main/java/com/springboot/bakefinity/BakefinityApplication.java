package com.springboot.bakefinity;

import com.springboot.bakefinity.repositories.interfaces.UserRepo;
import com.springboot.bakefinity.services.interfaces.CategoryService;
import com.springboot.bakefinity.services.interfaces.ProductService;
import com.springboot.bakefinity.services.interfaces.UserInterestsService;
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
	CommandLineRunner applicationInitializer(CategoryService service, UserRepo userRepo) {
		return (args) -> {
			System.out.println(service.getUserInterestedCategories(11));
		};
	}

}
