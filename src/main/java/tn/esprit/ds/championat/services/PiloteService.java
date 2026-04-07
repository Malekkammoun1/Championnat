package tn.esprit.ds.championat.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.ds.championat.entities.Pilote;
import tn.esprit.ds.championat.repositories.PiloteRepository;

import java.time.Year;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PiloteService implements IPiloteService {

    private final PiloteRepository piloteRepository;

    @Override
    public String addPilote(Pilote p) {
        if (p.getNbPointsGold() == null) p.setNbPointsGold(0);
        if (p.getClassementGeneral() == null) p.setClassementGeneral(0);
        Pilote saved = piloteRepository.save(p);
        return "Pilote ajouté avec succès. ID: " + saved.getIdPilote() + ", Nom: " + saved.getLibelle();
    }

    @Override
    public String affecterPiloteAEquipe(Long piloteId, Long equipeId) {
        // Implémentez si nécessaire
        return "";
    }

    // Calcul des points à partir des positions (via le repository)
    private int calculerPointsAnnuel(Pilote p, int annee) {
        Integer total = piloteRepository.sumPointsByPiloteAndAnnee(p.getIdPilote(), annee);
        return total != null ? total : 0;
    }

    @Scheduled(cron = "0 15 11 31 12 ?") // 31 décembre 11:15
    @Transactional
    public void mettreAJourPointsEtClassement() {
        log.info("=== EXÉCUTION DE mettreAJourPointsEtClassement ===");
        int annee = Year.now().getValue();
        log.info("Mise à jour des points pour l'année {}", annee);

        // 1. Mettre à jour nbPointsGold pour tous les pilotes
        List<Pilote> tous = piloteRepository.findAll();
        for (Pilote p : tous) {
            int points = calculerPointsAnnuel(p, annee);
            p.setNbPointsGold(points);
        }
        piloteRepository.saveAll(tous);

        // 2. Classement pour la catégorie "FORMULA1"
        String categorieCible = "FORMULA1";
        List<Pilote> classement = piloteRepository.findByCategorieOrderByNbPointsGoldDesc(categorieCible);
        int rang = 1;
        for (Pilote p : classement) {
            p.setClassementGeneral(rang++);
        }
        piloteRepository.saveAll(classement);

        // 3. Pour les autres catégories, classement = 0 (non classé)
        List<Pilote> autres = piloteRepository.findByCategorieNot(categorieCible);
        autres.forEach(p -> p.setClassementGeneral(0));
        piloteRepository.saveAll(autres);

        log.info("Classement mis à jour pour la catégorie {}", categorieCible);
    }

    // @PostConstruct commenté pour ne pas exécuter à chaque démarrage
}