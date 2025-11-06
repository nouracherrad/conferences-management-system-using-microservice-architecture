package org.sdia.conferenceservice.controller;
import org.sdia.conferenceservice.dto.KeynoteDTO;
import org.sdia.conferenceservice.entities.Conference;
import org.sdia.conferenceservice.feign.KeynoteRestClient;
import org.sdia.conferenceservice.service.ConferenceService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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
                .map(conf -> {
                    EntityModel<Conference> resource = EntityModel.of(conf);
                    resource.add(linkTo(methodOn(ConferenceController.class).getById(conf.getId())).withSelfRel());
                    resource.add(linkTo(methodOn(ConferenceController.class).getKeynotes(conf.getId())).withRel("keynotes"));
                    return resource;
                }).toList();
        return CollectionModel.of(conferences, linkTo(methodOn(ConferenceController.class).getAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Conference> getById(@PathVariable Long id) {
        Conference conf = service.getById(id);
        EntityModel<Conference> resource = EntityModel.of(conf);
        resource.add(linkTo(methodOn(ConferenceController.class).getById(id)).withSelfRel());
        resource.add(linkTo(methodOn(ConferenceController.class).getKeynotes(id)).withRel("keynotes"));
        return resource;
    }

    @GetMapping("/{id}/keynotes")
    public List<KeynoteDTO> getKeynotes(@PathVariable Long id) {
        Conference conf = service.getById(id);

        return conf.getKeynoteIds().stream()
                .map(keynoteClient::getKeynoteById) // retourne org.sdia.keynoteservice.entities.Keynote
                .map(k -> new KeynoteDTO(k.getId(), k.getNom(), k.getPrenom(), k.getEmail(), k.getFonction()))
                .toList();
    }


    @PostMapping
    public Conference create(@RequestBody Conference conference) { return service.save(conference); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
