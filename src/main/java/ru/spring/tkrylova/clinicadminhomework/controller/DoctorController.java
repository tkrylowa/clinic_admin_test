package ru.spring.tkrylova.clinicadminhomework.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.spring.tkrylova.clinicadminhomework.entity.Doctor;
import ru.spring.tkrylova.clinicadminhomework.entity.Specialization;
import ru.spring.tkrylova.clinicadminhomework.service.DoctorService;
import ru.spring.tkrylova.clinicadminhomework.service.SpecializationService;

@Controller
public class DoctorController {

  private final DoctorService doctorService;
  private final SpecializationService specializationService;

  public DoctorController(DoctorService doctorService,
      SpecializationService specializationService) {
    this.doctorService = doctorService;
    this.specializationService = specializationService;
  }

  @GetMapping("/doctors")
  public String getDoctors(Model model) {
    List<Doctor> doctors = doctorService.getDoctors();
    model.addAttribute("doctor_list", doctors);
    List<Specialization> specializations = specializationService.getSpecializations();
    model.addAttribute("specialization_list", specializations);
    return "doctor/doctors";
  }

  @GetMapping("/doctor/{id}")
  public String getDoctorById(@PathVariable int id, Model model) {
    Doctor doctor = doctorService.getDoctorById(id);
    model.addAttribute("doctor_info", doctor);
    return "doctor/doctor";
  }

  @GetMapping("/doctor/form")
  public String getDoctorAddForm(Doctor doctor) {
    return "doctor/doctor-add-form";
  }

  @PostMapping("/doctor")
  public String addDoctor(@Valid Doctor doctor,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "doctor/doctor-add-form";
    }
    doctorService.addDoctor(doctor);
    return "redirect:/doctor/form";
  }

  @GetMapping("/doctor/specialization/{code}")
  public String getDoctorSpecializationByCode(@PathVariable String code, Model model) {
    List<Doctor> doctors = doctorService.getDoctorsBySpecCode(code);
    List<Specialization> specializations = specializationService.getSpecializations();
    model.addAttribute("specialization_list", specializations);
    model.addAttribute("doctor_list", doctors);
    return "doctor/doctors";
  }
}