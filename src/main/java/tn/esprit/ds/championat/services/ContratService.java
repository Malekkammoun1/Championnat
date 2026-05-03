package tn.esprit.ds.championat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.ds.championat.dto.ContratResponseDto;
import tn.esprit.ds.championat.entities.Contrat;
import tn.esprit.ds.championat.entities.Equipe;
import tn.esprit.ds.championat.entities.Sponsor;
import tn.esprit.ds.championat.repositories.ContratRepository;
import tn.esprit.ds.championat.repositories.EquipeRepository;
import tn.esprit.ds.championat.repositories.SponsorRepository;

import java.time.LocalDate;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j

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
    @Scheduled(fixedRate = 30_000)
    @Transactional
    public void archiverContratsExpireesEtAffichageContratsActifsParEquipe() {

        int anneeCourante = Year.now().getValue();

        // 1) Archiver les contrats expirés (annee < année courante)
        List<Contrat> expires = contratRepository.findContratsExpires(anneeCourante);
        for (Contrat c : expires) {
            c.setArchived(true);
        }
        contratRepository.saveAll(expires);

        // 2) Afficher les contrats actifs PAR ÉQUIPE
        List<Contrat> actifs = contratRepository.findByArchivedFalse();

        // Regroupement par équipe (libelle de l'équipe)
        Map<String, List<Contrat>> contratsParEquipe = actifs.stream()
                .filter(c -> c.getEquipe() != null && c.getSponsor() != null)
                .collect(Collectors.groupingBy(c -> c.getEquipe().getLibelle()));

        for (Map.Entry<String, List<Contrat>> entry : contratsParEquipe.entrySet()) {
            String equipeLibelle = entry.getKey();
            for (Contrat c : entry.getValue()) {
                String sponsorNom = c.getSponsor().getNom();
                Float montant = c.getMontant() != null ? c.getMontant() : 0.0f;
                String montantFormate = String.format("%.1fE%d", montant / 1_000_000, 7);
                log.info("L'équipe {} a un contrat d'un montant de {} avec le sponsor {}",
                        equipeLibelle, montantFormate, sponsorNom);
            }
        }
    }

    @Override
    public HashMap<String, Float> historiqueContratsEquipe(String libelleEquipe) {
        Equipe equipe = equipeRepository.findByLibelle(libelleEquipe)
                .orElseThrow(() -> new RuntimeException("Equipe non trouvée"));
        List<Contrat> contrats = contratRepository.findByEquipeOrderByAnneeDesc(equipe);
        HashMap<String, Float> map = new HashMap<>();
        for (Contrat c : contrats) {
            if (c.getPilote() != null) {
                map.putIfAbsent(c.getPilote().getLibelle(), c.getMontant());
            }
        }
        return map;
    }



    @Override
    @Transactional
    public ContratResponseDto ajoutContratEtAffecterASponsorEtEquipe(Contrat contrat,
                                                                     String libelleEquipe,
                                                                     String nomSponsor,
                                                                     String pays) {
        // 1. Récupérer ou créer l'équipe
        Equipe equipe = equipeRepository.findByLibelle(libelleEquipe)
                .orElseThrow(() -> new RuntimeException("Équipe non trouvée : " + libelleEquipe));

        // 2. Récupérer ou créer le sponsor
        Sponsor sponsor = sponsorRepository.findByNom(nomSponsor)
                .orElseGet(() -> {
                    Sponsor s = new Sponsor();
                    s.setNom(nomSponsor);
                    s.setPays(pays);
                    return sponsorRepository.save(s);  // bien sauvegarder
                });

        // 3. Affecter et sauvegarder le contrat
        contrat.setEquipe(equipe);
        contrat.setSponsor(sponsor);
        Contrat saved = contratRepository.save(contrat);

        // 4. Construire et retourner le DTO
        return new ContratResponseDto(
                saved.getIdContrat(),
                saved.getMontant(),
                saved.getAnnee().toString(),   // si annee est Integer, convertir en String
                saved.getEquipe().getLibelle(),
                saved.getSponsor().getNom()
        );
    }
}