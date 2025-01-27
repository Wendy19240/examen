package com.taller.taller.services;

import com.taller.taller.entities.Asignatura;
import com.taller.taller.respositories.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaServiceImpl {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    public Asignatura save(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    public List<Asignatura> findAll() {
        return (List<Asignatura>) asignaturaRepository.findAll();
    }

    public Optional<Asignatura> findById(Long id) {
        return asignaturaRepository.findById(id);
    }

    public void delete(Long id) {
        asignaturaRepository.deleteById(id);
    }
}
