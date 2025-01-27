package com.taller.taller.services;

import com.taller.taller.entities.Profesor;
import com.taller.taller.respositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorServiceImpl {

    @Autowired
    private ProfesorRepository profesorRepository;

    public Profesor save(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public List<Profesor> findAll() {
        return (List<Profesor>) profesorRepository.findAll();
    }

    public Optional<Profesor> findById(Long id) {
        return profesorRepository.findById(id);
    }

    public void delete(Long id) {
        profesorRepository.deleteById(id);
    }
}
