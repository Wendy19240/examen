package com.taller.taller.services;

import com.taller.taller.entities.Asignatura;
import com.taller.taller.entities.Profesor;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface AsignaturaProfesorService {
    void asignarProfesor(Long asignaturaId, Long profesorId);
    void removerProfesor(Long asignaturaId, Long profesorId);
    List<Profesor> listarProfesoresDeAsignatura(Long asignaturaId);
    List<Asignatura> listarAsignaturasDeProfesor(Long profesorId);

    Asignatura saveAsignatura(@Valid Asignatura asignatura);

    List<Asignatura> findAllAsignaturas();

    Profesor saveProfesor(@Valid Profesor profesor);

    List<Profesor> findAllProfesores();

    Optional<Asignatura> findAsignaturaById(Long id);

    Optional<Profesor> findProfesorById(Long id);

    void deleteProfesor(Long id);

    void deleteAsignatura(Long id);
}
