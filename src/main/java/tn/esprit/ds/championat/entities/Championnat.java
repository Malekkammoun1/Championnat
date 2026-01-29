package tn.esprit.ds.championat.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Championnat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChampionnat;

    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    private String libelle;
    private Integer annee;

    // Relation avec DetailChampionnat
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_id")
    private DetailChampionnat detailChampionnat;

    // Relation avec Course
    @OneToMany(mappedBy = "championnat", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Course> courses;
}
