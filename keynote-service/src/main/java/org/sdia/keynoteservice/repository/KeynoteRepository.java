package org.sdia.keynoteservice.repository;

import org.sdia.keynoteservice.entities.keynote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeynoteRepository extends JpaRepository<keynote, Long> {
}
