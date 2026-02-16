package tn.esprit.ds.championat.controllers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tn.esprit.ds.championat.entities.Sponsor;
import tn.esprit.ds.championat.services.ISponsorService;
import java.util.List;


@Service
@RestController
@AllArgsConstructor
@RequestMapping("/sponsor")
public class SponsorController implements ISponsorService {

    private final ISponsorService sponsorService;

    @PostMapping("/add")
    public Sponsor ajouterSponsor(@RequestBody Sponsor s) {
        return sponsorService.ajouterSponsor(s);
    }

    @PostMapping("/add-multiple")
    public List<Sponsor> ajouterSponsors(@RequestBody List<Sponsor> sponsors) {
        return sponsorService.ajouterSponsors(sponsors);
    }

    @PutMapping("/update")
    public Sponsor modifierSponsor(@RequestBody Sponsor s) {
        return sponsorService.modifierSponsor(s);
    }

    @GetMapping("/get-all")
    public List<Sponsor> listSponsors() {
        return sponsorService.listSponsors();
    }

    @GetMapping("/get/{id}")
    public Sponsor recupererSponsor(@PathVariable Long id) {
        return sponsorService.recupererSponsor(id);
    }

    @DeleteMapping("/delete/{id}")
    public void supprimerSponsor(@PathVariable Long id) {
        sponsorService.supprimerSponsor(id);
    }

    @PutMapping("/archive/{id}")
    public Boolean archiverSponsor(@PathVariable Long id) {
        return sponsorService.archiverSponsor(id);
    }
}