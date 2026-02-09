package tn.esprit.ds.championat.repositories;

import tn.esprit.ds.championat.entities.Pilote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PiloteRepository extends JpaRepository<Pilote, Long> {

    // Trouver les pilotes par équipe
    List<Pilote> findByEquipeId(Long equipeId);

    // Trouver les pilotes par nom (libellé)
    List<Pilote> findByLibelleContainingIgnoreCase(String libelle);

    // Trouver les pilotes avec plus de X points
    List<Pilote> findByNbPointsGoldGreaterThan(Integer points);

    // Trouver les top N pilotes par points
    @Query("SELECT p FROM Pilote p ORDER BY p.nbPointsGold DESC")
    List<Pilote> findTopPilotes(@Param("limit") int limit);

    // Compter les pilotes par équipe
    long countByEquipeId(Long equipeId);

    // Trouver les pilotes sans équipe
    @Query("SELECT p FROM Pilote p WHERE p.equipe IS NULL")
    List<Pilote> findPilotesSansEquipe();
}