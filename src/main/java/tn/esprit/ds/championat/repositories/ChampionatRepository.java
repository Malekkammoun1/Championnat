package tn.esprit.ds.championat.repositories;

import tn.esprit.ds.championat.entities.Championnat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionatRepository extends JpaRepository<Championnat, Long> {
}