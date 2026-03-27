package tn.esprit.ds.championat.repositories;

import tn.esprit.ds.championat.entities.Pilote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PiloteRepository extends JpaRepository<Pilote, Long> {

    // CORRIGÉ: Trouver les pilotes par équipe (avec le bon nom de champ)
    List<Pilote> findByEquipeIdEquipe(Long idEquipe);

    // Alternative avec @Query si vous préférez garder le même nom de méthode
    @Query("SELECT p FROM Pilote p WHERE p.equipe.idEquipe = :idEquipe")
    List<Pilote> findByEquipeId(@Param("idEquipe") Long idEquipe);

    // Trouver les pilotes par nom (libellé) - OK
    List<Pilote> findByLibelleContainingIgnoreCase(String libelle);

    // Trouver les pilotes avec plus de X points - OK
    List<Pilote> findByNbPointsGoldGreaterThan(Integer points);

    // Trouver les top N pilotes par points - OK
    @Query("SELECT p FROM Pilote p ORDER BY p.nbPointsGold DESC")
    List<Pilote> findTopPilotes(@Param("limit") int limit);

    // CORRIGÉ: Compter les pilotes par équipe
    long countByEquipeIdEquipe(Long idEquipe);

    // Alternative avec @Query
    @Query("SELECT COUNT(p) FROM Pilote p WHERE p.equipe.idEquipe = :idEquipe")
    long countByEquipeId(@Param("idEquipe") Long idEquipe);

    // Trouver les pilotes sans équipe - OK
    @Query("SELECT p FROM Pilote p WHERE p.equipe IS NULL")
    List<Pilote> findPilotesSansEquipe();
}