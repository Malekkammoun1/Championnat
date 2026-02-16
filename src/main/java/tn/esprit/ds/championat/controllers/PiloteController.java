package tn.esprit.ds.championat.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.ds.championat.entities.Pilote;
import tn.esprit.ds.championat.services.PiloteService;

@RestController
@AllArgsConstructor
@RequestMapping("/pilote")
public class PiloteController {

    PiloteService ps;


    @PostMapping ("/add_Pilote")
    public String addPilote (@RequestBody Pilote pilote)
    {
        return ps.addPilote(pilote);
    }
}
