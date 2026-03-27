package tn.esprit.ds.championat.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.ds.championat.entities.Championnat;
import tn.esprit.ds.championat.entities.Course;
import tn.esprit.ds.championat.entities.DetailChampionnat;
import tn.esprit.ds.championat.repositories.ChampionatRepository;
import tn.esprit.ds.championat.repositories.ChampionatRepository;
import tn.esprit.ds.championat.repositories.CourseRepository;
import tn.esprit.ds.championat.repositories.DetailChampionatRepository;
import tn.esprit.ds.championat.repositories.DetailChampionatRepository;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class ChampionatService implements IChampionatService {

    private final ChampionatRepository championnatRepository;
    private final CourseRepository courseRepository;
    private final DetailChampionatRepository detailChampionnatRepository;

    /**
     * 1. Ajouter un championnat avec ses courses associées (ManyToMany)
     */
    @Override
    @Transactional
    public Championnat addChampionnatAndAssociatedCourses(Championnat championnat) {
        // Sauvegarder le championnat (parent)
        Championnat championnatSaved = championnatRepository.save(championnat);

        // Parcourir la liste des courses et les associer au championnat
        if (championnatSaved.getCourses() != null) {
            championnatSaved.getCourses().forEach(course -> {
                // Pour ManyToMany, il faut gérer les deux côtés
                if (course.getChampionnats() == null) {
                    course.setChampionnats(new HashSet<>());
                }
                course.getChampionnats().add(championnatSaved);
                courseRepository.save(course);
            });
        }

        return championnatSaved;
    }

    /**
     * 2. Ajouter un DetailChampionnat et l'affecter à un Championnat existant (OneToOne)
     */
    @Override
    @Transactional
    public Championnat ajouterEtAffecterDetailChampionnatAChampionnat(DetailChampionnat detailChampionnat, Long idChampionnat) {
        // Récupérer le championnat existant (parent)
        Championnat championnat = championnatRepository.findById(idChampionnat)
                .orElseThrow(() -> new RuntimeException("Championnat non trouvé avec l'id: " + idChampionnat));

        // Sauvegarder l'objet detail championnat dans la BD
        DetailChampionnat detailSaved = detailChampionnatRepository.save(detailChampionnat);

        // Affecter le child au parent (OneToOne)
        championnat.setDetailChampionnat(detailSaved);

        // Sauvegarder le nouvel état du championnat avec le détail affecté
        return championnatRepository.save(championnat);
    }

    /**
     * 3. Affecter une Course existante à un Championnat existant (ManyToMany)
     */
    @Override
    @Transactional
    public String affecterCourseAChampionnat(Long courseId, Long championnatId) {
        // Récupérer la course
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course non trouvée avec l'id: " + courseId));

        // Récupérer le championnat
        Championnat championnat = championnatRepository.findById(championnatId)
                .orElseThrow(() -> new RuntimeException("Championnat non trouvé avec l'id: " + championnatId));

        // Initialiser les sets si nécessaire
        if (course.getChampionnats() == null) {
            course.setChampionnats(new HashSet<>());
        }
        if (championnat.getCourses() == null) {
            championnat.setCourses(new HashSet<>());
        }

        // Ajouter la course au championnat (ManyToMany - côté championnat)
        championnat.getCourses().add(course);

        // Ajouter le championnat à la course (ManyToMany - côté course)
        course.getChampionnats().add(championnat);

        // Sauvegarder les deux entités
        courseRepository.save(course);
        championnatRepository.save(championnat);

        return "Course affectée avec succès au championnat: " + championnat.getLibelle();
    }
}