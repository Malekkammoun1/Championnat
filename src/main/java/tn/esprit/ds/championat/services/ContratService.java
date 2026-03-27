package tn.esprit.ds.championat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.ds.championat.entities.Contrat;
import tn.esprit.ds.championat.entities.Equipe;
import tn.esprit.ds.championat.entities.Sponsor;
import tn.esprit.ds.championat.repositories.ContratRepository;
import tn.esprit.ds.championat.repositories.EquipeRepository;
import tn.esprit.ds.championat.repositories.SponsorRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ContratService implements IContratService {

    private final ContratRepository contratRepository;
    private final SponsorRepository sponsorRepository;
    private final EquipeRepository equipeRepository;


     //Ajouter un contrat et l'affecter à un sponsor et une équipe

    @Override
    @Transactional
    public Contrat ajouterContratEtAffecterASponsorEtEquipe(Contrat contrat, Long sponsorId, Long equipeId) {
        // Récupérer le sponsor existant
        Sponsor sponsor = sponsorRepository.findById(sponsorId)
                .orElseThrow(() -> new RuntimeException("Sponsor non trouvé avec l'id: " + sponsorId));

        // Récupérer l'équipe existante
        Equipe equipe = equipeRepository.findById(equipeId)
                .orElseThrow(() -> new RuntimeException("Equipe non trouvée avec l'id: " + equipeId));

        // Initialiser les valeurs par défaut du contrat
        if (contrat.getMontant() == null) {
            contrat.setMontant(0.0f);
        }
        if (contrat.getArchived() == null) {
            contrat.setArchived(false);
        }

        // Affecter le sponsor et l'équipe au contrat
        contrat.setSponsor(sponsor);
        contrat.setEquipe(equipe);

        // Sauvegarder le contrat
        return contratRepository.save(contrat);
    }
}