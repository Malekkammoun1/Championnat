package tn.esprit.ds.championat.repositories;

import tn.esprit.ds.championat.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}