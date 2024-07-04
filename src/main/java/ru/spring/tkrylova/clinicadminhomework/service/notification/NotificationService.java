package ru.spring.tkrylova.clinicadminhomework.service.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import ru.spring.tkrylova.clinicadminhomework.entity.Doctor;
import ru.spring.tkrylova.clinicadminhomework.entity.Patient;
import ru.spring.tkrylova.clinicadminhomework.service.SendEmailService;


@Slf4j
public class NotificationService {
  private final SendEmailService emailService;

  public NotificationService(SendEmailService emailService) {
    this.emailService = emailService;
  }

  @Async("clinicExecutor")
  public void sendEmailAfterNewDoctorAppear(Patient patient, Doctor doctor) {
    emailService.sendEmailAfterNewDoctorAppear(patient, doctor);
  }
}