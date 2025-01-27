package com.taller.taller.services;


import com.taller.taller.entities.Asignatura;
import com.taller.taller.entities.Profesor;

import com.taller.taller.respositories.AsignaturaRepository;
import com.taller.taller.respositories.ProfesorRepository;
import com.taller.taller.services.AsignaturaProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaProfesorServiceImpl implements AsignaturaProfesorService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public void asignarProfesor(Long asignaturaId, Long profesorId) {
        Asignatura asignatura = asignaturaRepository.findById(asignaturaId).orElseThrow();
        Profesor profesor = profesorRepository.findById(profesorId).orElseThrow();
        asignatura.getProfesores().add(profesor);
        asignaturaRepository.save(asignatura);
    }

    @Override
    public void removerProfesor(Long asignaturaId, Long profesorId) {
        Asignatura asignatura = asignaturaRepository.findById(asignaturaId).orElseThrow();
        Profesor profesor = profesorRepository.findById(profesorId).orElseThrow();
        asignatura.getProfesores().remove(profesor);
        asignaturaRepository.save(asignatura);
    }

    @Override
    public List<Profesor> listarProfesoresDeAsignatura(Long asignaturaId) {
        Asignatura asignatura = asignaturaRepository.findById(asignaturaId).orElseThrow();
        return List.copyOf(asignatura.getProfesores());
    }

    @Override
    public List<Asignatura> listarAsignaturasDeProfesor(Long profesorId) {
        Profesor profesor = profesorRepository.findById(profesorId).orElseThrow();
        return List.copyOf(profesor.getAsignaturas());
    }

    @Override
    public Asignatura saveAsignatura(Asignatura asignatura) {
        return null;
    }

    @Override
    public List<Asignatura> findAllAsignaturas() {
        return List.of();
    }

    @Override
    public Profesor saveProfesor(Profesor profesor) {
        return null;
    }

    @Override
    public List<Profesor> findAllProfesores() {
        return List.of();
    }

    @Override
    public Optional<Asignatura> findAsignaturaById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Profesor> findProfesorById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteProfesor(Long id) {

    }

    @Override
    public void deleteAsignatura(Long id) {

    }
}
