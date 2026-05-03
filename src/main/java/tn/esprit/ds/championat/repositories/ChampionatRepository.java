package tn.esprit.ds.championat.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.ds.championat.dto.PiloteDto;
import tn.esprit.ds.championat.entities.Championnat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChampionatRepository extends JpaRepository<Championnat, Long> {

    List<Championnat> findByAnneeGreaterThan(Integer annee);

}

