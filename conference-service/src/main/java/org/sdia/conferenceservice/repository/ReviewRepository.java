package org.sdia.conferenceservice.repository;

import org.sdia.conferenceservice.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
