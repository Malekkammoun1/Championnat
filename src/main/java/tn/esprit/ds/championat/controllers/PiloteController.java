package tn.esprit.ds.championat.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.ds.championat.entities.Pilote;
import tn.esprit.ds.championat.services.IPiloteService;

@RestController
@AllArgsConstructor
@RequestMapping("/pilote")
@Tag(name = "Pilotes", description = "API pour la gestion des pilotes")
public class PiloteController {

    private final IPiloteService piloteService;

    @PostMapping("/add")
    @Operation(summary = "Ajouter un pilote")
    public String addPilote(@RequestBody Pilote pilote) {
        return piloteService.addPilote(pilote);
    }

    // Affecter un Pilote à une Equipe
    @PutMapping("/affecter-pilote-equipe/{piloteId}/{equipeId}")
    @Operation(summary = "Affecter un pilote existant à une équipe existante")
    public String affecterPiloteAEquipe(
            @PathVariable Long piloteId,
            @PathVariable Long equipeId) {
        return piloteService.affecterPiloteAEquipe(piloteId, equipeId);
    }
}