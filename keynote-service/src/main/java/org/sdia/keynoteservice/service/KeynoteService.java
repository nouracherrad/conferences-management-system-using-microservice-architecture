package org.sdia.keynoteservice.service;

import org.sdia.keynoteservice.entities.keynote;
import org.sdia.keynoteservice.repository.KeynoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class KeynoteService {
@Autowired
    private  KeynoteRepository repository;

    public KeynoteService(KeynoteRepository repository) {
        this.repository = repository;
    }

    public List<keynote> getAll() {
        return repository.findAll();
    }

    public keynote getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Keynote non trouvée"));
    }

    public keynote save(keynote keynote) {
        return repository.save(keynote);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}