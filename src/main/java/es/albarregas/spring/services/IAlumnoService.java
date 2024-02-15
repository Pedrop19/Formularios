package es.albarregas.spring.services;

import java.util.List;

import es.albarregas.spring.models.Alumno;

public interface IAlumnoService {
    public Alumno add(Alumno a);
    public List<Alumno> findAll();
    public Alumno findById(Integer id);
    public Alumno update(Alumno a);
    public void delete(Integer id);
}
