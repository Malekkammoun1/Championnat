package tn.esprit.ds.championat.services;

import tn.esprit.ds.championat.dto.PiloteDto;
import tn.esprit.ds.championat.entities.Championnat;
import tn.esprit.ds.championat.entities.DetailChampionnat;

import java.util.List;

public interface IChampionatService {
    Championnat addChampionnatAndAssociatedCourses(Championnat championnat);
    Championnat ajouterEtAffecterDetailChampionnatAChampionnat(DetailChampionnat detailChampionnat, Long idChampionnat);
    String affecterCourseAChampionnat(Long courseId, Long championnatId);
    void planifierMatch(String equipeA, String equipeB) throws InterruptedException;
    List<PiloteDto> listeWinners(Integer annee);
}
