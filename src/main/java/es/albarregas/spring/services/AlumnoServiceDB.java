package es.albarregas.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import es.albarregas.spring.models.Alumno;
import es.albarregas.spring.repositories.AlumnoRepository;

@Primary
@Service("alumnoServiceDB")
public class AlumnoServiceDB implements AlumnoService{

    @Autowired
    private AlumnoRepository repository;

    @Override
    public Alumno add(Alumno a) {
        return repository.save(a);
    }

    @Override
    public List<Alumno> findAll() {
        return repository.findAll();
    }

    @Override
    public Alumno findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Alumno update(Alumno a) {
        return repository.save(a);
    }

    @Override
    public void delete(Alumno a) {
        repository.delete(a);
    }

    @Override
    public List<Alumno> seeker(String str) {
        //? return repository.findByNombreContainsIgnoreCaseOrApellidosContainsIgnoreCase(str, str);
        //? return repository.findByNombreJPQL(str);
        return repository.findByNombreSQL(str);
    }
}
