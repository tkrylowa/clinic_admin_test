package ru.spring.tkrylova.clinicadminhomework.service.notification;


import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.spring.tkrylova.clinicadminhomework.service.PatientService;
import ru.spring.tkrylova.clinicadminhomework.service.SendEmailService;

@Slf4j
@Service
@EnableScheduling
public class ScheduleNotificationService {

  private final PatientService patientService;
  private final SendEmailService sendEmailService;

  public ScheduleNotificationService(PatientService patientService,
      SendEmailService sendEmailService) {
    this.patientService = patientService;
    this.sendEmailService = sendEmailService;
  }

  @Async("clinic-executor")
  @Scheduled(fixedRate = 28, timeUnit = TimeUnit.DAYS)
  public void sendEmailAfterNewDoctorAppear() {
    patientService.getPatientWithFeedbackInCurrentMonth()
        .forEach(sendEmailService::sendEmailWithDiscount);
  }
}