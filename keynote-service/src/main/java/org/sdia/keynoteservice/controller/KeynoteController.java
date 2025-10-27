package org.sdia.keynoteservice.controller;


import org.sdia.keynoteservice.entities.keynote;
import org.sdia.keynoteservice.service.KeynoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keynotes")
public class KeynoteController {


    private final KeynoteService service;

    public KeynoteController(KeynoteService service) {
        this.service = service;
    }

    @GetMapping
    public List<keynote> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public keynote getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public keynote create(@RequestBody keynote keynote) {
        return service.save(keynote);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}