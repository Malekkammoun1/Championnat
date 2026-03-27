package tn.esprit.ds.championat.services;

import tn.esprit.ds.championat.entities.Equipe;
import tn.esprit.ds.championat.entities.Pilote;

public interface IEquipeService {
    Equipe ajouterEquipe(Equipe equipe);

    // Nouveaux services
    Equipe affecterPiloteAEquipe(Long piloteId, Long equipeId);
}