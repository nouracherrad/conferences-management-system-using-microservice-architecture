package org.sdia.conferenceservice.repository;

import org.sdia.conferenceservice.entities.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {
}
