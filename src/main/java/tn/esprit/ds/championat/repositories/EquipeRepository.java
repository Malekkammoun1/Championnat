package tn.esprit.ds.championat.repositories;

import tn.esprit.ds.championat.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {

    // Vérifier l'existence par nom
    boolean existsByLibelle(String libelle);

    // Trouver les équipes par nom
    List<Equipe> findByLibelleContainingIgnoreCase(String libelle);

    // Trouver les équipes avec plus de X points
    List<Equipe> findByNbPointsTotalGreaterThan(Integer points);

    // Trouver les top N équipes par points
    @Query("SELECT e FROM Equipe e ORDER BY e.nbPointsTotal DESC")
    List<Equipe> findTopEquipes(@Param("limit") int limit);

    // Trouver les équipes par classement
    List<Equipe> findByClassementGeneralBetween(Integer min, Integer max);

    // Calculer le total des points de toutes les équipes
    @Query("SELECT SUM(e.nbPointsTotal) FROM Equipe e")
    Integer sumTotalPoints();

    // Dans EquipeRepository
    Optional<Equipe> findByLibelle(String libelle);
}