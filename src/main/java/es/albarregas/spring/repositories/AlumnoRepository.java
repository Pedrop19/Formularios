package es.albarregas.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.albarregas.spring.models.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer>{

}
