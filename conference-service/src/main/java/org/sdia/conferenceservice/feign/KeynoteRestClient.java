package org.sdia.conferenceservice.feign;
import org.sdia.conferenceservice.dto.KeynoteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "keynote-service")
public interface KeynoteRestClient {

    @GetMapping("/api/keynotes/{id}")
    KeynoteDTO getKeynoteById(@PathVariable Long id);

    @GetMapping("/api/keynotes")
    List<KeynoteDTO> getAllKeynotes();
}
