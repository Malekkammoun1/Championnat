package tn.esprit.ds.championat.repositories;

import tn.esprit.ds.championat.entities.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

    // Trouver les sponsors non archivés
    List<Sponsor> findByArchivedFalse();

    // Trouver les sponsors par pays
    List<Sponsor> findByPays(String pays);

    // Trouver les sponsors avec contrats non bloqués et non archivés
    List<Sponsor> findByBloquerContratFalseAndArchivedFalse();

    // Compter les sponsors par pays
    long countByPays(String pays);

    // Trouver les sponsors avec budget supérieur à une valeur
    List<Sponsor> findByBudgetAnnuelGreaterThan(Float budget);

    // Requête JPQL personnalisée
    @Query("SELECT s FROM Sponsor s WHERE s.dateCreation BETWEEN :startDate AND :endDate")
    List<Sponsor> findSponsorsCreesEntre(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    // Trouver les sponsors sans contrats
    @Query("SELECT s FROM Sponsor s WHERE s.contrats IS EMPTY")
    List<Sponsor> findSponsorsSansContrats();
}