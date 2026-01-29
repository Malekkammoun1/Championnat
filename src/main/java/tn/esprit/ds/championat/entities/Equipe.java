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
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipe;

    private String libelle;
    private Integer nbPointsTotal;
    private Integer classementGeneral;

    // Relation avec Pilote
    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Pilote> pilotes;

    // Relation avec Contrat
    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Contrat> contrats;
}