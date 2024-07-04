package ru.spring.tkrylova.clinicadminhomework.service.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.spring.tkrylova.clinicadminhomework.entity.Doctor;
import ru.spring.tkrylova.clinicadminhomework.service.PatientService;
import ru.spring.tkrylova.clinicadminhomework.service.SendEmailService;

@Slf4j
@Service
public class NotificationService {
  private final SendEmailService sendEmailService;
  private final PatientService patientService;

  public NotificationService(SendEmailService sendEmailService, PatientService patientService) {
    this.sendEmailService = sendEmailService;
    this.patientService = patientService;
  }

  @Async("clinic-executor")
  public void sendEmailAfterNewDoctorAppear(Doctor doctor) {
    int cnt = patientService.getCountOfPatients();
    int step = cnt / 100;
    if (step == 0) {
      patientService.getListOfPatientInPage(PageRequest.of(0, cnt));
    }
    while (step < cnt) {
      patientService.getListOfPatientInPage(PageRequest.of(0, step))
          .forEach(patient -> sendEmailService.sendEmailAfterNewDoctorAppear(patient, doctor));

      step += step + 1;
    }
  }
}