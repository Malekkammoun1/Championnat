package tn.esprit.ds.championat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.ds.championat.entities.Pilote;
import tn.esprit.ds.championat.repositories.PiloteRepository;

@Service
public class PiloteService implements IPiloteService {

    @Autowired
    private PiloteRepository piloteRepository;

    @Override
    public String addPilote(Pilote p) {
        // Validation des données
        if (p == null) {
            return "Erreur: Le pilote ne peut pas être null";
        }

        if (p.getLibelle() == null || p.getLibelle().trim().isEmpty()) {
            return "Erreur: Le libellé du pilote est requis";
        }

        // Initialisation des valeurs par défaut si null
        if (p.getNbPointsGold() == null) {
            p.setNbPointsGold(0);
        }

        if (p.getClassementGeneral() == null) {
            p.setClassementGeneral(0);
        }

        try {
            // Sauvegarder le pilote
            Pilote savedPilote = piloteRepository.save(p);

            // Retourner un message de succès avec l'ID
            return "Pilote ajouté avec succès! ID: " + savedPilote.getIdPilote()
                    + ", Nom: " + savedPilote.getLibelle();

        } catch (Exception e) {
            return "Erreur lors de l'ajout du pilote: " + e.getMessage();
        }
    }

}