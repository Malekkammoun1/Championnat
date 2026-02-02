package tn.esprit.ds.championat.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCourse;

    private String emplacement;

    @Temporal(TemporalType.DATE)
    private Date dateCourse;

    // Relation avec Championnat
    @ManyToMany(mappedBy = "courses")
    @ToString.Exclude
    private Set<Championnat> championnats;

    // Relation avec Position
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Position> positions;
}