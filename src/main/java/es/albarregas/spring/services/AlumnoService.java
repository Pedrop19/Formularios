package es.albarregas.spring.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import es.albarregas.spring.models.Alumno;

@Service
public class AlumnoService implements IAlumnoService{
	
	private List<Alumno> alumnos = new ArrayList<>();
	
	public Alumno add(Alumno a) {
		alumnos.add(a);
		return a;
	}
	
	public List<Alumno> findAll() {
		return alumnos;
	}

	public Alumno findById(Integer id) {
		return alumnos.stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
	}

	public Alumno update(Alumno a) {
		Alumno alumno = findById(a.getId());
		alumno.setNombre(a.getNombre());
		alumno.setApellidos(a.getApellidos());
		alumno.setEmail(a.getEmail());
		alumno.setTieneBeca(a.getTieneBeca());
		return alumno;
	}

	public void delete(Integer id) {
		alumnos.removeIf(a -> a.getId().equals(id));
	}
	
	@PostConstruct
	public void init() {
		alumnos.addAll(
				Arrays.asList(new Alumno(1, "Fernando", "Benítez Cano", "fermando@gmail.com"), 
						new Alumno(2, "Sergio", "García Cortés", "sergio@gmail.com"), 
						new Alumno(3, "Patricia", "Martínez Flores", "raul@gmail.com"))
				);
	}

}
