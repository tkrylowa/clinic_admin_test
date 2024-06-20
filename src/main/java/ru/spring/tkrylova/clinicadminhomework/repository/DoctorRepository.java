package ru.spring.tkrylova.clinicadminhomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.spring.tkrylova.clinicadminhomework.entity.Doctor;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    List<Doctor> findAllByActiveEquals(boolean active);

    @Query(nativeQuery = true, value = "SELECT count(*) FROM doctor d " +
            "WHERE d.specialization_id = :specialization_id AND d.is_active = :active")
    int countByActiveEqualsAndSpecializationsEquals(boolean active, int specialization_id);
}