package ru.spring.tkrylova.clinicadminhomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.spring.tkrylova.clinicadminhomework.entity.Doctor;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    List<Doctor> findAllByIsActiveEquals(boolean active);

    @Query(nativeQuery = true, value = "SELECT d.* FROM doctor d "
        + " JOIN doctor_specialization ds ON d.id=ds.doctor_id"
        + " JOIN specialization s "
        + " on ds.specialization_id = s.id" +
        " WHERE s.code_latin = :code")
    List<Doctor> findAllBySpecializationsCode(String code);

    @Query(nativeQuery = true, value = "SELECT count(*) FROM doctor d " +
            "WHERE d.specialization_id = :specialization_id AND d.is_active = :active")
    int countByIsActiveEqualsAndSpecializationsEquals(boolean active, int specialization_id);
}