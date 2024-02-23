package es.albarregas.spring.services;

import java.util.List;

import es.albarregas.spring.models.Alumno;

public interface AlumnoService {
    public Alumno add(Alumno a);
    public List<Alumno> findAll();
    public Alumno findById(int id);
    public Alumno update(Alumno a);
    public void delete(Alumno a);
}
