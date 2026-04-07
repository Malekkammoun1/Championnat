package tn.esprit.ds.championat.repositories;

import tn.esprit.ds.championat.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {
    // Somme des points d'un pilote pour une année donnée
    @Query("SELECT SUM(p.nbPoints) FROM Position p WHERE p.pilote.idPilote = :piloteId AND YEAR(p.course.dateCourse) = :annee")
    Integer sumPointsByPiloteAndAnnee(@Param("piloteId") Long piloteId, @Param("annee") int annee);

    // Récupérer toutes les positions d'une course
    List<Position> findByCourseIdCourse(Long courseId);
}