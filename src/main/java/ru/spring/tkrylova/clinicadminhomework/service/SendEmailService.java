package ru.spring.tkrylova.clinicadminhomework.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import ru.spring.tkrylova.clinicadminhomework.entity.Doctor;
import ru.spring.tkrylova.clinicadminhomework.entity.Patient;

@Slf4j
public class SendEmailService {

  private final JavaMailSender javaMailSender;

  public SendEmailService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  public void sendEmailWithDiscount(Patient patient) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom("admin@yandex.ru");
    mailMessage.setTo(patient.getEmail());
    mailMessage.setSubject("Our news!");
    String emailText = String.format("You have discount 10 percent! Use code: '%s'",
        patient.getUniqueCode());
    mailMessage.setText(emailText);
    javaMailSender.send(mailMessage);
    getInfo(patient, emailText);
  }

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
    getInfo(patient, emailText);
  }

  private void getInfo(Patient patient, String emailText) {
    log.info("New email with text {} was sent to patient {}", emailText, patient.getEmail());
  }

}