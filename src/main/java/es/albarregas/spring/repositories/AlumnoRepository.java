package es.albarregas.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import es.albarregas.spring.models.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer>{

    List<Alumno> findByNombreContainsIgnoreCaseOrApellidosContainsIgnoreCase(String nombre, String apellidos);

    @Query("SELECT a FROM Alumno a WHERE lower(a.nombre) like %:cadena% or lower(a.apellidos) like %:cadena%")
    List<Alumno> findByNombreJPQL(String cadena);

    @Query(value="select * from alumnos where lower(nombre) like %:cadena%", nativeQuery=true)
    List<Alumno> findByNombreSQL(String cadena);
}
