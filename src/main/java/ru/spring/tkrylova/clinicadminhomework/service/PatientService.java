package ru.spring.tkrylova.clinicadminhomework.service;

import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.spring.tkrylova.clinicadminhomework.entity.Patient;
import ru.spring.tkrylova.clinicadminhomework.repository.PatientRepository;

@Service
public class PatientService {

  private final PatientRepository patientRepository;


  public PatientService(PatientRepository patientRepository) {
    this.patientRepository = patientRepository;
  }

  public List<Patient> getListOfPatientInPage(Pageable page) {
    return patientRepository.findAll(page).getContent();
  }

  public int getCountOfPatients() {
    return patientRepository.countAll();
  }

  public List<Patient> getPatientWithFeedbackInCurrentMonth(){
    List<Patient> patients = patientRepository.getPatientsByFeedbackInCurrentMonth();
    patients.forEach(p-> {
      p.setUniqueCode(RandomStringUtils.randomAlphanumeric(12));
      patientRepository.save(p);
    });
    return patientRepository.getPatientsByFeedbackInCurrentMonth();
  }
}