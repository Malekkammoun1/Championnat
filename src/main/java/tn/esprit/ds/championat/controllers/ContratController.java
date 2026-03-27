package tn.esprit.ds.championat.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.ds.championat.entities.Contrat;
import tn.esprit.ds.championat.services.IContratService;

@RestController
@AllArgsConstructor
@RequestMapping("/contrat")
@Tag(name = "Contrats", description = "API pour la gestion des contrats")
public class ContratController {

    private final IContratService contratService;

    @PostMapping("/add-and-affect/{sponsorId}/{equipeId}")
    @Operation(summary = "Ajouter un contrat et l'affecter à un sponsor et une équipe")
    public Contrat ajouterContratEtAffecterASponsorEtEquipe(
            @RequestBody Contrat contrat,
            @PathVariable Long sponsorId,
            @PathVariable Long equipeId) {
        return contratService.ajouterContratEtAffecterASponsorEtEquipe(contrat, sponsorId, equipeId);
    }
}