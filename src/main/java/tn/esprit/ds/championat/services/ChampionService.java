package tn.esprit.ds.championat.services;

import tn.esprit.ds.championat.dto.PiloteDto;
import tn.esprit.ds.championat.entities.Championnat;
import tn.esprit.ds.championat.repositories.ChampionatRepository;
import tn.esprit.ds.championat.repositories.PositionRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChampionService implements IChampionService {

    private ChampionatRepository championnatRepository;
    private PositionRepository positionRepository;

    public List<PiloteDto> listeWinners(Integer annee) {
        List<Championnat> championnats = championnatRepository.findByAnneeGreaterThan(annee);
        List<PiloteDto> result = new ArrayList<>();
        for (Championnat c : championnats) {
            positionRepository.findGagnantByChampionnatId(c.getIdChampionnat())
                    .ifPresent(gagnant -> {
                        result.add(new PiloteDto(
                                gagnant.getLibelle(),  // nom du pilote
                                c.getLibelle(),         // nom du championnat
                                c.getAnnee()
                        ));
                    });
        }
        return result;
    }
}