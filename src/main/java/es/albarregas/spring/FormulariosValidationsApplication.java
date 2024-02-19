package es.albarregas.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.albarregas.spring.upload.storage.StorageService;

@SpringBootApplication
public class FormulariosValidationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormulariosValidationsApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

}
