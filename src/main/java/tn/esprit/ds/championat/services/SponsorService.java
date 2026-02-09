package tn.esprit.ds.championat.services;

import tn.esprit.ds.championat.entities.Sponsor;
import tn.esprit.ds.championat.repositories.SponsorRepository;
import tn.esprit.ds.championat.services.ISponsorService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SponsorService implements ISponsorService {

    private SponsorRepository  sponsorRepository;

    @Override
    public Sponsor ajouterSponsor(Sponsor sponsor) {

        sponsor.setArchived(false);
        sponsor.setBloquerContrat(false);
        sponsor.setDateCreation(LocalDate.from(LocalDateTime.now()));
        return sponsorRepository.save(sponsor);
    }

    @Override
    public List<Sponsor> ajouterSponsors(List<Sponsor> sponsors) {
        List<Sponsor> sponsorsInitialises = sponsors.stream()
                .peek(sponsor -> {
                    sponsor.setDateCreation(LocalDate.from(LocalDateTime.now()));  // LocalDateTime au lieu de LocalDate
                    sponsor.setArchived(false);
                    sponsor.setBloquerContrat(false);
                })
                .collect(Collectors.toList());
        return sponsorRepository.saveAll(sponsorsInitialises);
    }

    @Override
    public Sponsor modifierSponsor(Sponsor sponsor) {
        sponsor.setDateCreation(LocalDate.from(LocalDateTime.now()));
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
        sp.setArchived(true);
        sponsorRepository.save(sp);
        return true;
    }

}