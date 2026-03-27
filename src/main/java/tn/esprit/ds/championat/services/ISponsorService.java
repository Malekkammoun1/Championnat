package tn.esprit.ds.championat.services;

import tn.esprit.ds.championat.entities.Sponsor;

import java.util.List;

public interface ISponsorService {
    Sponsor ajouterSponsor(Sponsor sponsor);
    List<Sponsor> ajouterSponsors(List<Sponsor> sponsors);
    Sponsor modifierSponsor(Sponsor sponsor);
    void supprimerSponsor(Long idSponsor);
    List<Sponsor> listSponsors();
    Sponsor recupererSponsor(Long idSponsor);
    Boolean archiverSponsor(Long idSponsor);
}