package com.taller.taller.controllers;

import com.taller.taller.entities.Asignatura;
import com.taller.taller.entities.Profesor;
import com.taller.taller.services.AsignaturaProfesorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asignaturas-profesores")
public class AsignaturaProfesorController {

    @Autowired
    private AsignaturaProfesorService asignaturaProfesorService;

    // Crear una asignatura
    @PostMapping("/asignatura")
    public ResponseEntity<Asignatura> crearAsignatura(@Valid @RequestBody Asignatura asignatura) {
        Asignatura nuevaAsignatura = asignaturaProfesorService.saveAsignatura(asignatura);
        return ResponseEntity.status(201).body(nuevaAsignatura);
    }

    // Crear un profesor
    @PostMapping("/profesor")
    public ResponseEntity<Profesor> crearProfesor(@Valid @RequestBody Profesor profesor) {
        Profesor nuevoProfesor = asignaturaProfesorService.saveProfesor(profesor);
        return ResponseEntity.status(201).body(nuevoProfesor);
    }

    // Listar todas las asignaturas
    @GetMapping("/asignaturas")
    public ResponseEntity<List<Asignatura>> listarAsignaturas() {
        List<Asignatura> asignaturas = asignaturaProfesorService.findAllAsignaturas();
        return ResponseEntity.ok(asignaturas);
    }

    // Listar todos los profesores
    @GetMapping("/profesores")
    public ResponseEntity<List<Profesor>> listarProfesores() {
        List<Profesor> profesores = asignaturaProfesorService.findAllProfesores();
        return ResponseEntity.ok(profesores);
    }

    // Buscar una asignatura por su ID
    @GetMapping("/asignatura/{id}")
    public ResponseEntity<Asignatura> buscarAsignaturaPorId(@PathVariable Long id) {
        Optional<Asignatura> asignatura = asignaturaProfesorService.findAsignaturaById(id);
        return asignatura.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Buscar un profesor por su ID
    @GetMapping("/profesor/{id}")
    public ResponseEntity<Profesor> buscarProfesorPorId(@PathVariable Long id) {
        Optional<Profesor> profesor = asignaturaProfesorService.findProfesorById(id);
        return profesor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar una asignatura
    @PutMapping("/asignatura/{id}")
    public ResponseEntity<Asignatura> actualizarAsignatura(
            @PathVariable Long id, @Valid @RequestBody Asignatura asignatura) {
        Optional<Asignatura> asignaturaOptional = asignaturaProfesorService.findAsignaturaById(id);
        if (asignaturaOptional.isPresent()) {
            asignatura.setId(id);
            Asignatura asignaturaActualizada = asignaturaProfesorService.saveAsignatura(asignatura);
            return ResponseEntity.ok(asignaturaActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    // Actualizar un profesor
    @PutMapping("/profesor/{id}")
    public ResponseEntity<Profesor> actualizarProfesor(
            @PathVariable Long id, @Valid @RequestBody Profesor profesor) {
        Optional<Profesor> profesorOptional = asignaturaProfesorService.findProfesorById(id);
        if (profesorOptional.isPresent()) {
            profesor.setId(id);
            Profesor profesorActualizado = asignaturaProfesorService.saveProfesor(profesor);
            return ResponseEntity.ok(profesorActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar una asignatura
    @DeleteMapping("/asignatura/{id}")
    public ResponseEntity<Void> eliminarAsignatura(@PathVariable Long id) {
        if (asignaturaProfesorService.findAsignaturaById(id).isPresent()) {
            asignaturaProfesorService.deleteAsignatura(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar un profesor
    @DeleteMapping("/profesor/{id}")
    public ResponseEntity<Void> eliminarProfesor(@PathVariable Long id) {
        if (asignaturaProfesorService.findProfesorById(id).isPresent()) {
            asignaturaProfesorService.deleteProfesor(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Asignar un profesor a una asignatura
    @PostMapping("/asignar")
    public ResponseEntity<?> asignarProfesor(
            @RequestParam Long asignaturaId,
            @RequestParam Long profesorId) {
        asignaturaProfesorService.asignarProfesor(asignaturaId, profesorId);
        return ResponseEntity.ok("Profesor asignado a la asignatura con éxito.");
    }

    // Remover un profesor de una asignatura
    @DeleteMapping("/remover")
    public ResponseEntity<?> removerProfesor(
            @RequestParam Long asignaturaId,
            @RequestParam Long profesorId) {
        asignaturaProfesorService.removerProfesor(asignaturaId, profesorId);
        return ResponseEntity.ok("Profesor removido de la asignatura con éxito.");
    }

    // Listar profesores de una asignatura
    @GetMapping("/profesores/{asignaturaId}")
    public ResponseEntity<List<Profesor>> listarProfesores(@PathVariable Long asignaturaId) {
        List<Profesor> profesores = asignaturaProfesorService.listarProfesoresDeAsignatura(asignaturaId);
        return profesores.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(profesores);
    }

    // Listar asignaturas de un profesor
    @GetMapping("/asignaturas/{profesorId}")
    public ResponseEntity<List<Asignatura>> listarAsignaturas(@PathVariable Long profesorId) {
        List<Asignatura> asignaturas = asignaturaProfesorService.listarAsignaturasDeProfesor(profesorId);
        return asignaturas.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(asignaturas);
    }
}
