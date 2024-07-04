package ru.spring.tkrylova.clinicadminhomework.service.notification;


import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.spring.tkrylova.clinicadminhomework.entity.Patient;
import ru.spring.tkrylova.clinicadminhomework.service.PatientService;

@Slf4j
@Service
@EnableScheduling
public class ScheduleNotificationService {

  private final PatientService patientService;

  private final JavaMailSender javaMailSender;

  public ScheduleNotificationService(PatientService patientService, JavaMailSender javaMailSender) {
    this.patientService = patientService;
    this.javaMailSender = javaMailSender;
  }

  @Async("clinicExecutor")
  @Scheduled(fixedRate = 28, timeUnit = TimeUnit.DAYS)
  public void sendEmailAfterNewDoctorAppear() {
    patientService.getPatientWithFeedbackInCurrentMonth().forEach(this::sendEmail);
  }

  public void sendEmail(Patient patient) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom("admin@yandex.ru");
    mailMessage.setTo(patient.getEmail());
    mailMessage.setSubject("Our news!");
    String emailText = String.format("You have discount by 10 percent! Use code: '%s'",
        patient.getUniqueCode());
    mailMessage.setText(emailText);
    javaMailSender.send(mailMessage);
    log.info("New email with text {} was sent to patient {}", emailText, patient.getEmail());
  }
}