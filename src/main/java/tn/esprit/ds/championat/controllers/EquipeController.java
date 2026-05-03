package tn.esprit.ds.championat.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.ds.championat.entities.Equipe;
import tn.esprit.ds.championat.services.IEquipeService;

@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
@Tag(name = "Équipes", description = "API pour la gestion des équipes")
public class EquipeController {
    private final IEquipeService equipeService;

    @PostMapping("/add")
    @Operation(summary = "Ajouter une nouvelle équipe")
    public Equipe ajouterEquipe(@RequestBody Equipe e) {

        return equipeService.ajouterEquipe(e);
    }

    // Affecter un pilote à une équipe
    @PutMapping("/affecter-pilote/{piloteId}/{equipeId}")
    @Operation(summary = "Affecter un pilote existant à une équipe existante")
    public Equipe affecterPiloteAEquipe(
            @PathVariable Long piloteId,
            @PathVariable Long equipeId) {
        return equipeService.affecterPiloteAEquipe(piloteId, equipeId);
    }
}