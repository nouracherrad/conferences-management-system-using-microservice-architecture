package org.sdia.keynoteservice;

import org.sdia.keynoteservice.entities.keynote;
import org.sdia.keynoteservice.repository.KeynoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KeynoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeynoteServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner runner(KeynoteRepository repository) {
        return args -> {
            keynote k1 = keynote.builder()
                    .nom("Alice")
                    .prenom("Martin")
                    .email("alice@example.com")
                    .fonction("Speaker")
                    .build();

            keynote k2 = keynote.builder()
                    .nom("Bob")
                    .prenom("Durand")
                    .email("bob@example.com")
                    .fonction("Expert")
                    .build();

            repository.save(k1);
            repository.save(k2);
        };
    }
}
