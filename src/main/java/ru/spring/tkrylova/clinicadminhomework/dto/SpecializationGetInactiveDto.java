package ru.spring.tkrylova.clinicadminhomework.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class SpecializationGetInactiveDto {
    private String specializationName;
    private String specializationCode;
    private String description;
    private LocalDateTime createdAt;
    private boolean isActive;
    private int numberOfActiveDoctors;
    private int numberOfInActiveDoctors;
}
