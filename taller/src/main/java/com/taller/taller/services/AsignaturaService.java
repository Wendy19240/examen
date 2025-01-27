package com.taller.taller.services;

import com.taller.taller.entities.Asignatura;

import java.util.List;
import java.util.Optional;

public interface AsignaturaService {
    List<Asignatura> findAll();
    Optional<Asignatura> findById(Long id);
    Asignatura save(Asignatura usuario);
    void deleteById(Long id);
}
