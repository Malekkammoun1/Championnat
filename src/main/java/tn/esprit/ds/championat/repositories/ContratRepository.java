package tn.esprit.ds.championat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.ds.championat.entities.Contrat;
import tn.esprit.ds.championat.entities.Equipe;
import tn.esprit.ds.championat.entities.Pilote;

import java.util.List;

public interface ContratRepository extends JpaRepository<Contrat, Long> {

    List<Contrat> findByArchivedFalse();

    // Tri par année (String) décroissante
    List<Contrat> findByEquipeOrderByAnneeDesc(Equipe equipe);

    // Contrats expirés : archived=false et annee < année courante
    @Query("select c from Contrat c where c.archived = false and cast(c.annee as int) < :anneeCourante")
    List<Contrat> findContratsExpires(@Param("anneeCourante") int anneeCourante);
}