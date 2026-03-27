package tn.esprit.ds.championat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.ds.championat.entities.Sponsor;
import tn.esprit.ds.championat.repositories.SponsorRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SponsorService implements ISponsorService {

    private final SponsorRepository sponsorRepository;

    @Override
    public Sponsor ajouterSponsor(Sponsor sponsor) {
        sponsor.setArchived(false);
        sponsor.setBloquerContrat(false);
        sponsor.setDateCreation(LocalDate.now());
        sponsor.setDateDerniereModification(LocalDate.now());
        return sponsorRepository.save(sponsor);
    }

    @Override
    public List<Sponsor> ajouterSponsors(List<Sponsor> sponsors) {
        List<Sponsor> sponsorsInitialises = sponsors.stream()
                .peek(sponsor -> {
                    sponsor.setDateCreation(LocalDate.now());
                    sponsor.setDateDerniereModification(LocalDate.now());
                    sponsor.setArchived(false);
                    sponsor.setBloquerContrat(false);
                })
                .collect(Collectors.toList());
        return sponsorRepository.saveAll(sponsorsInitialises);
    }

    @Override
    public Sponsor modifierSponsor(Sponsor sponsor) {
        sponsor.setDateDerniereModification(LocalDate.now());
        return sponsorRepository.save(sponsor);
    }

    @Override
    public void supprimerSponsor(Long idSponsor) {
        sponsorRepository.deleteById(idSponsor);
    }

    @Override
    public List<Sponsor> listSponsors() {
        return sponsorRepository.findAll();
    }

    @Override
    public Sponsor recupererSponsor(Long idSponsor) {
        return sponsorRepository.findById(idSponsor).orElse(null);
    }

    @Override
    public Boolean archiverSponsor(Long idSponsor) {
        Sponsor sp = recupererSponsor(idSponsor);
        if (sp != null) {
            sp.setArchived(true);
            sp.setDateDerniereModification(LocalDate.now());
            sponsorRepository.save(sp);
            return true;
        }
        return false;
    }
}