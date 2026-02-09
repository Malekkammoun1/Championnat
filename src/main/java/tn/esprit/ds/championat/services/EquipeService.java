package tn.esprit.ds.championat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.ds.championat.entities.Equipe;
import tn.esprit.ds.championat.repositories.EquipeRepository;

@Service
public class EquipeService implements IEquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    @Override
    public Equipe ajouterEquipe(Equipe equipe) {
        // Validation
        if (equipe == null) {
            throw new IllegalArgumentException("L'équipe ne peut pas être null");
        }

        if (equipe.getLibelle() == null || equipe.getLibelle().trim().isEmpty()) {
            throw new IllegalArgumentException("Le libellé de l'équipe est requis");
        }

        // Initialisation des valeurs par défaut
        if (equipe.getNbPointsTotal() == null) {
            equipe.setNbPointsTotal(0);
        }

        if (equipe.getClassementGeneral() == null) {
            equipe.setClassementGeneral(0);
        }

        // Vérifier si une équipe avec le même nom existe déjà
        if (equipeRepository.existsByLibelle(equipe.getLibelle())) {
            throw new RuntimeException("Une équipe avec le nom '" + equipe.getLibelle() + "' existe déjà");
        }

        return equipeRepository.save(equipe);
    }

}