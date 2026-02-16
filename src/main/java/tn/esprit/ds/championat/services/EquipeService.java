package tn.esprit.ds.championat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.ds.championat.entities.Equipe;
import tn.esprit.ds.championat.repositories.EquipeRepository;

@Service
@RequiredArgsConstructor
public class EquipeService implements IEquipeService {
    private final EquipeRepository equipeRepository;

    @Override
    public Equipe ajouterEquipe(Equipe equipe) {
        // Initialisation des valeurs par défaut
        if (equipe.getNbPointsTotal() == null) {
            equipe.setNbPointsTotal(0);
        }
        if (equipe.getClassementGeneral() == null) {
            equipe.setClassementGeneral(0);
        }

        return equipeRepository.save(equipe);
    }
}