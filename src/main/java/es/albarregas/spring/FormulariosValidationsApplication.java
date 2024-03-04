package es.albarregas.spring;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
			// repository.save(new Alumno("Fernando", "Benítez Cano", "fernando@gmail.com", false));
			// repository.save(new Alumno("Sergio", "García Cortés", "sergio@gmail.com", false));

			// repository.findAll().forEach(System.out::println);

			// repository.saveAll(Arrays.asList(new Alumno("Fernando", "Benítez Cano", "fernando@gmail.com", false),
			// 		new Alumno("Sergio", "García Cortés", "sergio@gmail.com", false),
			// 		new Alumno("Patricia", "García Cortés", "patricia@gmail.com", false),
			// 		new Alumno("Alberto", "García Cortés", "alberto@gmail.com", false)));

			// repository.findAll().forEach(System.out::println);

			List<String> nombres = Arrays.asList("Jose", "Maria", "Isabel", "Juan", "Carmen", "Alberto", "Susana", "Jose", "Pedro", "Jesus");
			List<String> apellidos = Arrays.asList("Garcia", "Fernandez", "Lopez", "Martinez", "Gonzalez", "Rodriguez", "Sanchez", "Perez", "Martin", "Gomez");

			Collections.shuffle(nombres);

			repository.saveAll(IntStream.rangeClosed(1, nombres.size()).mapToObj((i) ->  {
				String nombre = nombres.get(i - 1);
				String apellido1 = apellidos.get(ThreadLocalRandom.current().nextInt(apellidos.size()));
				String apellido2 = apellidos.get(ThreadLocalRandom.current().nextInt(apellidos.size()));
				return new Alumno(String.format("%s", nombre), String.format("%s %s", apellido1, apellido2),
					String.format("%s.%s@iesalbarregas,es", nombre.toLowerCase(), apellido1.toLowerCase()), true);
			}).collect(Collectors.toList()));
		
		};
	}

}
