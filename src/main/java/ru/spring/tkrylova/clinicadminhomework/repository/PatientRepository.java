package ru.spring.tkrylova.clinicadminhomework.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.spring.tkrylova.clinicadminhomework.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Page<Patient> findAll(Pageable pageable);
    int countAll();
    @Query(nativeQuery = true, value = "SELECT p.* FROM patient p JOIN feedback f "
        + "ON p.patient_id=f.patient_id" +
        "WHERE f.created_at >= DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)")
    List<Patient> getPatientsByFeedbackInCurrentMonth();
}