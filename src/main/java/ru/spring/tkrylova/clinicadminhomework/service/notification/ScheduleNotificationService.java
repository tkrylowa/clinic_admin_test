package ru.spring.tkrylova.clinicadminhomework.service.notification;


import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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
  private final SendEmailService emailService;

  public ScheduleNotificationService(PatientService patientService) {
    this.patientService = patientService;
    this.emailService = new SendEmailService(new JavaMailSenderImpl());
  }

  @Async("clinicExecutor")
  @Scheduled(fixedRate = 28, timeUnit = TimeUnit.DAYS)
  public void sendEmailAfterNewDoctorAppear() {
    patientService.getPatientWithFeedbackInCurrentMonth().forEach(
        emailService::sendEmailWithDiscount);
  }

}