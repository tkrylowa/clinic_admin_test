package ru.spring.tkrylova.clinicadminhomework.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.spring.tkrylova.clinicadminhomework.entity.Doctor;
import ru.spring.tkrylova.clinicadminhomework.repository.DoctorRepository;

@Service
public class DoctorService {
    private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public int getCountActiveInactiveDoctors(boolean isActive) {
        return doctorRepository.findAllByIsActiveEquals(isActive).size();
    }

    public Doctor getDoctorById(int id) {
        Doctor doctor = new Doctor();
        doctor.setId(1);
        doctor.setActive(true);
        doctor.setFirstName("Ivan");
        doctor.setLastName("Ivanov");
        doctor.setExperience(15);
        return doctorRepository.findById(id).orElse(doctor);
    }

    public List<Doctor> getDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        if (!doctors.isEmpty()) {
            Doctor doctor01 = new Doctor();
            doctor01.setId(1);
            doctor01.setActive(true);
            doctor01.setFirstName("Ivan");
            doctor01.setLastName("Ivanov");
            doctor01.setExperience(15);
            Doctor doctor02 = new Doctor();
            doctor01.setId(2);
            doctor01.setActive(true);
            doctor01.setFirstName("Evgenii");
            doctor01.setLastName("Ivanov");
            doctor01.setExperience(15);
            doctors.add(doctor01);
            doctors.add(doctor02);
        }
        return doctors;
    }

    public List<Doctor> getDoctorsBySpecCode(String code) {
        List<Doctor> doctors = doctorRepository.findAllBySpecializationsCode(code);
        if (!doctors.isEmpty()) {
            Doctor doctor01 = new Doctor();
            doctor01.setId(1);
            doctor01.setActive(true);
            doctor01.setFirstName("Ivan");
            doctor01.setLastName("Ivanov");
            doctor01.setExperience(15);
            Doctor doctor02 = new Doctor();
            doctor01.setId(2);
            doctor01.setActive(true);
            doctor01.setFirstName("Evgenii");
            doctor01.setLastName("Ivanov");
            doctor01.setExperience(15);
            doctors.add(doctor01);
            doctors.add(doctor02);
        }
        return doctors;
    }

    public void addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }
}