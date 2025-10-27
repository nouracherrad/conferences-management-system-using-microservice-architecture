package org.sdia.conferenceservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor  @AllArgsConstructor  @Data @Getter @Setter  @Builder
public class Conference {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Type type;
    private Date date;
    private int duration;
    private int nbInscrits;
    private int score;
  @ElementCollection
  private List<Long> keynoteIds;
  @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL)
  private List<Review> reviews;
}
