package tn.esprit.ds.championat.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.ds.championat.entities.Championnat;
import tn.esprit.ds.championat.entities.DetailChampionnat;
import tn.esprit.ds.championat.services.IChampionatService;

@RestController
@AllArgsConstructor
@RequestMapping("/championnat")
@Tag(name = "Championnats", description = "API pour la gestion des championnats")
public class ChampionatController {

    private final IChampionatService championnatService;

    @PostMapping("/add-with-courses")
    @Operation(summary = "Ajouter un championnat avec ses courses associées")
    public Championnat addChampionnatAndAssociatedCourses(@RequestBody Championnat championnat) {
        return championnatService.addChampionnatAndAssociatedCourses(championnat);
    }

    @PutMapping("/add-detail-to-championnat/{idChampionnat}")
    @Operation(summary = "Ajouter un détail championnat et l'affecter à un championnat existant")
    public Championnat ajouterEtAffecterDetailChampionnatAChampionnat(
            @RequestBody DetailChampionnat detailChampionnat,
            @PathVariable Long idChampionnat) {
        return championnatService.ajouterEtAffecterDetailChampionnatAChampionnat(detailChampionnat, idChampionnat);
    }

    @PutMapping("/affecter-course/{courseId}/{championnatId}")
    @Operation(summary = "Affecter une course existante à un championnat existant")
    public String affecterCourseAChampionnat(
            @PathVariable Long courseId,
            @PathVariable Long championnatId) {
        return championnatService.affecterCourseAChampionnat(courseId, championnatId);
    }
}