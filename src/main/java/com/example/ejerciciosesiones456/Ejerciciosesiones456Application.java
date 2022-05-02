package com.example.ejerciciosesiones456;

import com.example.ejerciciosesiones456.entities.Laptop;
import com.example.ejerciciosesiones456.repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Ejerciciosesiones456Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Ejerciciosesiones456Application.class, args);
		LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);
		Laptop laptop = new Laptop();
		laptop.setId(null);
		laptop.setName("MacBook Pro 2021");
		laptop.setBrand("Apple");
		laptop.setModel("Pro 21");
		laptop.setPrice(2000.00);
		laptopRepository.save(laptop);
	}
}

//Otra forma de precargar la DB
/*package payroll;

		import org.slf4j.Logger;
		import org.slf4j.LoggerFactory;
		import org.springframework.boot.CommandLineRunner;
		import org.springframework.context.annotation.Bean;
		import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(LaptopRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
			log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
		};
	}
}*/
