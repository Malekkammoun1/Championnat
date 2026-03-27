package tn.esprit.ds.championat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.ds.championat.entities.Pilote;
import tn.esprit.ds.championat.repositories.PiloteRepository;

@Service
@RequiredArgsConstructor
public class PiloteService implements IPiloteService {
    private final PiloteRepository piloteRepository;

    @Override
    public String addPilote(Pilote p) {
        // Initialisation des valeurs par défaut
        if (p.getNbPointsGold() == null) {
            p.setNbPointsGold(0);
        }
        if (p.getClassementGeneral() == null) {
            p.setClassementGeneral(0);
        }
        Pilote saved = piloteRepository.save(p);
        return "Pilote ajouté avec succès. ID: " + saved.getIdPilote() + ", Nom: " + saved.getLibelle();
    }
}