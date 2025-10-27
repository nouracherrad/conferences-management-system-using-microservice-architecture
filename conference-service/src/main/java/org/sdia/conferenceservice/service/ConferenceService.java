package org.sdia.conferenceservice.service;

import org.sdia.conferenceservice.entities.Conference;
import org.sdia.conferenceservice.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceService {

    private final  ConferenceRepository repository;

    public ConferenceService(ConferenceRepository repository) {
        this.repository = repository;
    }

    public List<Conference> getAll() { return repository.findAll(); }

    public Conference getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Conférence non trouvée"));
    }

    public Conference save(Conference conf) {
        return repository.save(conf);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}