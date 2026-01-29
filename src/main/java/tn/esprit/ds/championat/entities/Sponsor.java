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
public class Sponsor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSponsor;

    private String nom;
    private String pays;
    private Float budgetAnnuel;
    private Boolean bloquerContrat;

    // Relation avec Contrat
    @OneToMany(mappedBy = "sponsor", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Contrat> contrats;
}