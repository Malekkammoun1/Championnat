package tn.esprit.ds.championat.repositories;

import tn.esprit.ds.championat.entities.DetailChampionnat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailChampionatRepository extends JpaRepository<DetailChampionnat, Long> {
}