package com.taller.taller.services;

import com.taller.taller.entities.Reclamo;
import com.taller.taller.respositories.ReclamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReclamoServicesImpl implements ReclamoService {

    @Autowired
    private ReclamoRepository repository;

    @Override
    public List<Reclamo> findAll() {
        return (List<Reclamo>) repository.findAll();
    }

    @Override
    public Optional<Reclamo> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Reclamo save(Reclamo reclamo) {
        return repository.save(reclamo);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);

    }
}
