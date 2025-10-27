package org.sdia.conferenceservice;

import org.sdia.conferenceservice.entities.Conference;
import org.sdia.conferenceservice.entities.Review;
import org.sdia.conferenceservice.entities.Type;
import org.sdia.conferenceservice.repository.ConferenceRepository;
import org.sdia.conferenceservice.repository.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
@EnableFeignClients
@SpringBootApplication
public class ConferenceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner runner(ConferenceRepository repository, ReviewRepository reviewRepository) {
        return args -> {
            Conference c1 = Conference.builder()
                    .id(null)
                    .title("Spring Boot")
                    .type(Type.ACADEMIQUE)
                    .date(new Date())
                    .duration(3)
                    .nbInscrits(0)
                    .score(0)
                    .reviews(new ArrayList<>())
                    .build();

            Conference c2 = Conference.builder()
                    .id(null)
                    .title("Microservices")
                    .type(Type.COMMERCIALE)
                    .date(new Date())
                    .duration(3)
                    .nbInscrits(0)
                    .score(0)
                    .reviews(new ArrayList<>())
                    .build();

            repository.save(c1);
            repository.save(c2);
            Review review1 = Review.builder()
                    .date(new Date())
                    .texte("Excellent workshop!")
                    .note(5)
                    .conference(c1)
                    .build();

            Review review2 = Review.builder()
                    .date(new Date())
                    .texte("Très instructif")
                    .note(4)
                    .conference(c2)
                    .build();

            reviewRepository.save(review1);
            reviewRepository.save(review2);

            // Création d'une Review pour Microservices
            Review review3 = Review.builder()
                    .date(new Date())
                    .texte("Bon contenu mais trop court")
                    .note(3)
                    .conference(c1)
                    .build();

            reviewRepository.save(review3);
        };
    }
}

