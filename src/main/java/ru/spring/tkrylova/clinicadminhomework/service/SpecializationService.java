package ru.spring.tkrylova.clinicadminhomework.service;

import org.springframework.stereotype.Service;
import ru.spring.tkrylova.clinicadminhomework.dto.SpecializationDto;
import ru.spring.tkrylova.clinicadminhomework.dto.SpecializationGetInactiveDto;
import ru.spring.tkrylova.clinicadminhomework.entity.Specialization;
import ru.spring.tkrylova.clinicadminhomework.repository.DoctorRepository;
import ru.spring.tkrylova.clinicadminhomework.repository.SpecializationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecializationService {
    private final SpecializationRepository specializationRepository;
    private final DoctorRepository specializationDoctorRepository;

    public SpecializationService(SpecializationRepository specializationRepository, DoctorRepository specializationDoctorRepository) {
        this.specializationRepository = specializationRepository;
        this.specializationDoctorRepository = specializationDoctorRepository;
    }

    public int createSpecialization(Specialization specialization) {
        return specializationRepository.save(specialization).getId();
    }

    public int createSpecializationDto(SpecializationDto specializationDto) {
        Specialization specialization = Specialization.builder()
                .code(specializationDto.getSpecializationCode())
                .name(specializationDto.getSpecializationName())
                .description(specializationDto.getDescription()).build();
        return specializationRepository.save(specialization).getId();
    }

    public List<SpecializationGetInactiveDto> getSpecializations(boolean isActive) {
        List<Specialization> spec = specializationRepository.findAllByActiveEquals(isActive);
        List<SpecializationGetInactiveDto> specializationDtos = new ArrayList<>();
        spec.forEach(s -> {
            int numberInactiveDocs = specializationDoctorRepository.countByActiveEqualsAndSpecializationsEquals(false, s.getId());
            int numberActiveDocs = specializationDoctorRepository.countByActiveEqualsAndSpecializationsEquals(true, s.getId());

            SpecializationGetInactiveDto specializationDto = SpecializationGetInactiveDto.builder()
                    .description(s.getDescription())
                    .specializationCode(s.getCode())
                    .isActive(s.isActive())
                    .createdAt(s.getCreatedAt())
                    .specializationName(s.getName())
                    .numberOfActiveDoctors(numberActiveDocs)
                    .numberOfInActiveDoctors(numberInactiveDocs)
                    .build();
            specializationDtos.add(specializationDto);
        });
        return specializationDtos;
    }
}