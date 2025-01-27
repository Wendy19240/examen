package com.taller.taller.services;



import com.taller.taller.entities.Reclamo;

import java.util.List;
import java.util.Optional;

public interface ReclamoService {
    List<Reclamo> findAll();
    Optional<Reclamo> findById(Long id);
    Reclamo save(Reclamo reclamo);
    void deleteById(Long id);
}
