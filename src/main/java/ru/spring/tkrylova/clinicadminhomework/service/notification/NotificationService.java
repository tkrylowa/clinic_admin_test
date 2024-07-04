package ru.spring.tkrylova.clinicadminhomework.service.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.spring.tkrylova.clinicadminhomework.entity.Doctor;
import ru.spring.tkrylova.clinicadminhomework.entity.Patient;


@Slf4j
@Service
public class NotificationService {
  private final JavaMailSender javaMailSender;

  public NotificationService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  @Async("clinicExecutor")
  public void sendEmailAfterNewDoctorAppear(Patient patient, Doctor doctor) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom("admin@yandex.ru");
    mailMessage.setTo(patient.getEmail());
    mailMessage.setSubject(
        String.format("New doctor %s %s appears!", doctor.getLastName(), doctor.getFirstName()));
    String emailText = String.format(
        "New doctor %s has spec: %s! You can make an appointment, if you want. Call to us right now@ ",
        doctor.getLastName(),
        doctor.getSpecializations().toString());
    mailMessage.setText(emailText);
    javaMailSender.send(mailMessage);
    log.info("New email with text {} was sent to patient {}", emailText, patient.getEmail());
  }
}