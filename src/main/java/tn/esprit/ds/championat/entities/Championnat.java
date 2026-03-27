package tn.esprit.ds.championat.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Championnat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChampionnat;

    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    private String libelle;
    private Integer annee;

    // Relation avec DetailChampionnat
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_id")
    private DetailChampionnat detailChampionnat;

    // Relation avec Course
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "championnat_course",
            joinColumns = @JoinColumn(name = "championnat_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @ToString.Exclude
    private Set<Course> courses;
}
