package tn.esprit.ds.championnat.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.ds.championat.entities.Equipe;
import tn.esprit.ds.championat.services.IEquipeService;

@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
public class EquipeController {
    private final IEquipeService equipeService;

    @PostMapping("/add")
    public Equipe ajouterEquipe(@RequestBody Equipe e) {
        return equipeService.ajouterEquipe(e);
    }
}