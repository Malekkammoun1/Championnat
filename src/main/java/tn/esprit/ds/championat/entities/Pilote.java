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
public class Pilote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPilote;

    private String libelle;
    private Integer nbPointsGold;
    private Integer classementGeneral;

    private String categorie;

    // Relation avec Equipe
    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    // Relation avec Position
    @OneToMany(mappedBy = "pilote", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Position> positions;
}