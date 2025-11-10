package org.sdia.conferenceservice.controller;
import org.sdia.conferenceservice.dto.KeynoteDTO;
import org.sdia.conferenceservice.entities.Conference;
import org.sdia.conferenceservice.feign.KeynoteRestClient;
import org.sdia.conferenceservice.service.ConferenceService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@RestController
@RequestMapping("/api/conferences")

public class ConferenceController {

    private final ConferenceService service;
    private final KeynoteRestClient keynoteClient;

    public ConferenceController(ConferenceService service, KeynoteRestClient keynoteClient) {
        this.service = service;
        this.keynoteClient = keynoteClient;
    }

    @GetMapping
    public CollectionModel<EntityModel<Conference>> getAll() {
        List<EntityModel<Conference>> conferences = service.getAll().stream()
                .map(conf -> EntityModel.of(conf))
                .toList();
        return CollectionModel.of(conferences);
    }


    @GetMapping("/{id}")
    public Conference getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/{id}/keynotes")
    public List<KeynoteDTO> getKeynotes(@PathVariable Long id) {
        Conference conf = service.getById(id);

        return conf.getKeynoteIds().stream()
                .map(keynoteClient::getKeynoteById)
                .map(k -> new KeynoteDTO(k.getId(), k.getNom(), k.getPrenom(), k.getEmail(), k.getFonction()))
                .toList();
    }

    @PostMapping
    public Conference create(@RequestBody Conference conference) {
        return service.save(conference);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
@GetMapping("/auth")
    public Authentication authentication(Authentication authentication){
        return authentication;
}


}
