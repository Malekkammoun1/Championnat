package tn.esprit.ds.championat.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    private Float montant;
    private String annee;
    private Boolean archived;

    // Relation avec Sponsor
    @ManyToOne
    @JoinColumn(name = "sponsor_id")
    private Sponsor sponsor;

    // Relation avec Equipe
    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;
}