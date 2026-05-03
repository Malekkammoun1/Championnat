// dossier : tn.esprit.ds.championat.dto
package tn.esprit.ds.championat.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContratResponseDto {
    private Long idContrat;
    private Float montant;
    private String annee;          // ou Integer selon ton entité
    private String libelleEquipe;
    private String nomSponsor;

    // Constructeur
    public ContratResponseDto(Long idContrat, Float montant, String annee,
                              String libelleEquipe, String nomSponsor) {
        this.idContrat = idContrat;
        this.montant = montant;
        this.annee = annee;
        this.libelleEquipe = libelleEquipe;
        this.nomSponsor = nomSponsor;
    }

}