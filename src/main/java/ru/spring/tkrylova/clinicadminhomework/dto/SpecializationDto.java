package ru.spring.tkrylova.clinicadminhomework.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SpecializationDto {
    private String specializationName;
    private String specializationCode;
    private String description;
    private boolean enable;
}
