package org.sdia.conferenceservice.controller;
import org.sdia.conferenceservice.entities.Conference;
import org.sdia.conferenceservice.entities.KeynoteClient;
import org.sdia.conferenceservice.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/conferences")
public class ConferenceController {
    private  final ConferenceService service;
    private  final KeynoteClient keynoteClient;
    public ConferenceController(ConferenceService service, KeynoteClient keynoteClient) {
        this.service = service;
        this.keynoteClient = keynoteClient;
    }


    @GetMapping
    public List<Conference> getAll() { return service.getAll(); }

    @GetMapping("/{id}/keynotes")
    public List<?> getKeynotes(@PathVariable Long id) {
        Conference conf = service.getById(id);
        return conf.getKeynoteIds().stream()
                .map(keynoteClient::getKeynoteById)
                .toList();
    }
    @PostMapping
    public Conference create(@RequestBody Conference conference) { return service.save(conference); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
