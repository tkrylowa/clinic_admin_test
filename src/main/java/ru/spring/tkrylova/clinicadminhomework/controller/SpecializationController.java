package ru.spring.tkrylova.clinicadminhomework.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.spring.tkrylova.clinicadminhomework.dto.SpecializationGetInactiveDto;
import ru.spring.tkrylova.clinicadminhomework.entity.Specialization;
import ru.spring.tkrylova.clinicadminhomework.service.SpecializationService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@Validated
@RequestMapping("/specialization")
public class SpecializationController {
    private final SpecializationService specializationService;

    public SpecializationController(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSpecialization(@RequestBody @Valid Specialization spec) throws URISyntaxException {
        int id = specializationService.createSpecialization(spec);
        URI location = new URI(String.format("http://localhost:8080/specialization/%s", id));
        return ResponseEntity.created(location)
                .build();
    }

    @GetMapping("/inactive")
    public List<SpecializationGetInactiveDto> getInactiveSpecialization() {
        return specializationService.getSpecializations(false);
    }
}
