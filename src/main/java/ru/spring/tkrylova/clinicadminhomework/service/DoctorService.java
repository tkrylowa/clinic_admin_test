package ru.spring.tkrylova.clinicadminhomework.service;

import org.springframework.stereotype.Service;
import ru.spring.tkrylova.clinicadminhomework.repository.DoctorRepository;

@Service
public class DoctorService {
    private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public int getCountActiveInactiveDoctors(boolean isActive) {
        return doctorRepository.findAllByActiveEquals(isActive).size();
    }
}