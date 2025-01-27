package com.taller.taller.services;

import com.taller.taller.entities.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorService {
    List<Profesor> findAll();
    Optional<Profesor> findById(Long id);
    Profesor save(Profesor rol);
    void deleteById(Long id);
}
