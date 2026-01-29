package tn.esprit.ds.championat.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPosition;

    private Integer classement;
    private Integer nbPoints;

    // Relation avec Course
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // Relation avec Pilote
    @ManyToOne
    @JoinColumn(name = "pilote_id")
    private Pilote pilote;
}