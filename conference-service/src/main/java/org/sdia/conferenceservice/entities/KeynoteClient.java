package org.sdia.conferenceservice.entities;

import org.sdia.keynoteservice.entities.keynote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "keynote-service")
public interface KeynoteClient {
    @GetMapping("/keynotes/{id}")
    keynote getKeynoteById(@PathVariable Long id);

    @GetMapping("/keynotes")
    List<keynote> getAllKeynotes();
}