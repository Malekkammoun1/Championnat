package tn.esprit.ds.championat.repositories;

import org.springframework.data.jpa.repository.Query;
import tn.esprit.ds.championat.entities.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
    List<Contrat> findByArchivedFalse();

    // Archive = false ET annee < anneeCourante (annee est String -> cast en int)
    @Query("select c from Contrat c where c.archived = false and cast(c.annee as int) < :anneeCourante")
    List<Contrat> findContratsExpires(int anneeCourante);


}