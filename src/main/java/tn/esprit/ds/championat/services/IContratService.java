package tn.esprit.ds.championat.services;

import tn.esprit.ds.championat.entities.Contrat;

public interface IContratService {
    Contrat ajouterContratEtAffecterASponsorEtEquipe(Contrat contrat, Long sponsorId, Long equipeId);
}