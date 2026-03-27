package tn.esprit.ds.championat.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.ds.championat.entities.Sponsor;
import tn.esprit.ds.championat.services.ISponsorService;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/sponsor")
@Tag(name = "Sponsors", description = "API pour la gestion des sponsors")
public class SponsorController {  // ← Plus de "implements"

    private final ISponsorService sponsorService;

    @PostMapping("/add")
    @Operation(summary = "Ajouter un sponsor")
    public Sponsor ajouterSponsor(@RequestBody Sponsor s) {
        return sponsorService.ajouterSponsor(s);
    }

    @PostMapping("/add-multiple")
    @Operation(summary = "Ajouter plusieurs sponsors")
    public List<Sponsor> ajouterSponsors(@RequestBody List<Sponsor> sponsors) {
        return sponsorService.ajouterSponsors(sponsors);
    }

    @PutMapping("/update")
    @Operation(summary = "Modifier un sponsor")
    public Sponsor modifierSponsor(@RequestBody Sponsor s) {
        return sponsorService.modifierSponsor(s);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Lister tous les sponsors")
    public List<Sponsor> listSponsors() {
        return sponsorService.listSponsors();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Récupérer un sponsor par ID")
    public Sponsor recupererSponsor(@PathVariable Long id) {
        return sponsorService.recupererSponsor(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Supprimer un sponsor")
    public void supprimerSponsor(@PathVariable Long id) {
        sponsorService.supprimerSponsor(id);
    }

    @PutMapping("/archive/{id}")
    @Operation(summary = "Archiver un sponsor")
    public Boolean archiverSponsor(@PathVariable Long id) {
        return sponsorService.archiverSponsor(id);
    }
}