package es.albarregas.spring;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.albarregas.spring.models.Alumno;
import es.albarregas.spring.repositories.AlumnoRepository;
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

	@Bean
	CommandLineRunner initData(AlumnoRepository repository) {
		return (args) -> {
			repository.save(new Alumno("Fernando", "Benítez Cano", "fernando@gmail.com", false));
			repository.save(new Alumno("Sergio", "García Cortés", "sergio@gmail.com", false));

			repository.findAll().forEach(System.out::println);

			repository.saveAll(Arrays.asList(new Alumno("Fernando", "Benítez Cano", "fernando@gmail.com", false),
					new Alumno("Sergio", "García Cortés", "sergio@gmail.com", false),
					new Alumno("Patricia", "García Cortés", "patricia@gmail.com", false),
					new Alumno("Alberto", "García Cortés", "alberto@gmail.com", false)));

			repository.findAll().forEach(System.out::println);
		};
	}

}
