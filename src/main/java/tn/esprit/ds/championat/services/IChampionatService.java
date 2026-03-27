package tn.esprit.ds.championat.services;

import tn.esprit.ds.championat.entities.Championnat;
import tn.esprit.ds.championat.entities.DetailChampionnat;

public interface IChampionatService {
    Championnat addChampionnatAndAssociatedCourses(Championnat championnat);
    Championnat ajouterEtAffecterDetailChampionnatAChampionnat(DetailChampionnat detailChampionnat, Long idChampionnat);
    String affecterCourseAChampionnat(Long courseId, Long championnatId);
}