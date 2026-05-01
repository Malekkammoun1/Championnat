package tn.esprit.ds.championat.services;

import tn.esprit.ds.championat.entities.Contrat;

import java.util.HashMap;

public interface IContratService {
    Contrat ajouterContratEtAffecterASponsorEtEquipe(Contrat contrat, Long sponsorId, Long equipeId);
    HashMap<String, Float> historiqueContratsEquipe(String libelleEquipe);
}