package tn.esprit.ds.championat.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.ds.championat.entities.Pilote;
import tn.esprit.ds.championat.entities.Position;
import tn.esprit.ds.championat.repositories.PiloteRepository;
import tn.esprit.ds.championat.repositories.PositionRepository;

import java.time.Year;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepository positionRepository;
    private final PiloteRepository piloteRepository;

    // Ajouter ou modifier une position
    @Transactional
    public Position savePosition(Position position) {
        Position saved = positionRepository.save(position);
        // Mettre à jour les points du pilote concerné
        mettreAJourPointsPilote(saved.getPilote().getIdPilote());
        return saved;
    }

    // Supprimer une position
    @Transactional
    public void deletePosition(Long id) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Position non trouvée"));
        Long piloteId = position.getPilote().getIdPilote();
        positionRepository.deleteById(id);
        mettreAJourPointsPilote(piloteId);
    }

    // Recalculer les points d'un pilote à partir de ses positions
    private void mettreAJourPointsPilote(Long piloteId) {
        Pilote p = piloteRepository.findById(piloteId).orElseThrow();
        int annee = Year.now().getValue();
        Integer totalPoints = positionRepository.sumPointsByPiloteAndAnnee(piloteId, annee);
        p.setNbPointsGold(totalPoints != null ? totalPoints : 0);
        piloteRepository.save(p);
        // Recalculer le classement de sa catégorie
        recalculerClassementCategorie(p.getCategorie());
    }

    // Recalculer le classement pour une catégorie donnée
    private void recalculerClassementCategorie(String categorie) {
        List<Pilote> pilotes = piloteRepository.findByCategorieOrderByNbPointsGoldDesc(categorie);
        int rang = 1;
        for (Pilote p : pilotes) {
            p.setClassementGeneral(rang++);
        }
        piloteRepository.saveAll(pilotes);
    }
}