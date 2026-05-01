package tn.esprit.ds.championat.services;

import tn.esprit.ds.championat.entities.Pilote;

import java.time.LocalDate;

public interface IPiloteService {
    String addPilote(Pilote p);

    String affecterPiloteAEquipe(Long piloteId, Long equipeId);

    Integer nbPointsParPilotesUneEquipeChampionnatPourUneAnne(Long idEquipe, Integer annee);
    Float moyennePositionsEntreDeuxDate(LocalDate startDate, LocalDate endDate, String libellePilote);
}