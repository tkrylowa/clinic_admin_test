package ru.spring.tkrylova.clinicadminhomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.tkrylova.clinicadminhomework.entity.Specialization;

import java.util.List;

public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
    List<Specialization> findAllByActiveEquals(boolean active);
    List<Specialization> findAllByActiveFalse();
}
