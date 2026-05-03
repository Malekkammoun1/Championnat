package tn.esprit.ds.championat.repositories;

import tn.esprit.ds.championat.entities.Pilote;
import tn.esprit.ds.championat.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query("SELECT SUM(p.nbPoints) FROM Position p WHERE p.pilote.idPilote = :piloteId AND YEAR(p.course.dateCourse) = :annee")
    Integer sumPointsByPiloteAndAnnee(@Param("piloteId") Long piloteId, @Param("annee") int annee);

    List<Position> findByCourseIdCourse(Long courseId);

    @Query("SELECT p FROM Position p WHERE p.pilote = :pilote AND p.course.dateCourse BETWEEN :startDate AND :endDate")
    List<Position> findByPiloteAndCourseDateBetween(@Param("pilote") Pilote pilote,
                                                    @Param("startDate") LocalDate startDate,
                                                    @Param("endDate") LocalDate endDate);

    @Query("SELECT AVG(p.classement) FROM Position p WHERE p.pilote = :pilote AND p.course.dateCourse BETWEEN :startDate AND :endDate")
    Float averagePositionByPiloteAndDateBetween(@Param("pilote") Pilote pilote,
                                                @Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);



     //Retourne le pilote gagnant d'un championnat (celui qui a le plus de points cumulés)

    // Supprimer le mot-clé static
    @Query("SELECT p.pilote " +
            "FROM Position p " +
            "WHERE p.course IN (SELECT c FROM Course c JOIN c.championnats ch WHERE ch.idChampionnat = :champId) " +
            "GROUP BY p.pilote " +
            "ORDER BY SUM(p.nbPoints) DESC")
    Optional<Pilote> findGagnantByChampionnatId(@Param("champId") Long champId);
}