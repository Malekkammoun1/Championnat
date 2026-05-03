package tn.esprit.ds.championat.dto;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PiloteDto {
    private String nomPilote;       // Pilote.libelle
    private String nomChampionnat;  // Championnat.libelle
    private Integer annee;

    public PiloteDto(String nomPilote, String nomChampionnat, Integer annee) {
        this.nomPilote = nomPilote;
        this.nomChampionnat = nomChampionnat;
        this.annee = annee;
    }
    // getters / setters
}