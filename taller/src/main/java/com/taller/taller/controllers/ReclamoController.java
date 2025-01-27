package com.taller.taller.controllers;

import com.taller.taller.entities.Reclamo;
import com.taller.taller.services.ReclamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reclamos")
public class ReclamoController {
    @Autowired
    private ReclamoService reclamoService;

    @PostMapping
    public ResponseEntity<?> crear(@Validated @RequestBody Reclamo reclamo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reclamoService.save(reclamo));
    }

    @GetMapping
    public List<Reclamo> listar() {
        return reclamoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Reclamo> reclamoOptional = reclamoService.findById(id);
        if (reclamoOptional.isPresent()) {
            return ResponseEntity.ok().body(reclamoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Reclamo reclamo, @PathVariable Long id) {
        Optional<Reclamo> reclamoOptional = reclamoService.findById(id);
        if (reclamoOptional.isPresent()) {
            Reclamo reclamoDB = reclamoOptional.get();
            reclamoDB.setCliente(reclamo.getCliente());
            reclamoDB.setDescripcion(reclamo.getDescripcion());
            reclamoDB.setPrioridad(reclamo.getPrioridad());
            reclamoDB.setFechaReclamo(reclamo.getFechaReclamo());
            return ResponseEntity.status(HttpStatus.CREATED).body(reclamoService.save(reclamoDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Reclamo> reclamoOptional = reclamoService.findById(id);
        if (reclamoOptional.isPresent()) {
            reclamoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
