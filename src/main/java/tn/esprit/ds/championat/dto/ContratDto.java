package tn.esprit.ds.championat.dto;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class ContratDto {
    private String sponsorNom;
    private String equipeLibelle;
    private String paysSponsor;
    private Double montant;
    private Date dateDebut;

    public ContratDto(String sponsorNom, String equipeLibelle, String paysSponsor, Double montant, Date dateDebut) {
        this.sponsorNom = sponsorNom;
        this.equipeLibelle = equipeLibelle;
        this.paysSponsor = paysSponsor;
        this.montant = montant;
        this.dateDebut = dateDebut;
    }

}