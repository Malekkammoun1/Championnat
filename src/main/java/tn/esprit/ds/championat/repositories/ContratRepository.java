package tn.esprit.ds.championat.repositories;

import tn.esprit.ds.championat.entities.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
}