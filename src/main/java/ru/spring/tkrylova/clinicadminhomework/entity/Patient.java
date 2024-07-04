package ru.spring.tkrylova.clinicadminhomework.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "patient")
public class Patient {

  @Id
  @Column(name = "patient_id")
  private long id;

  private String email;

  private String uniqueCode;
  @Column(name = "is_active", insertable = false, columnDefinition = "BOOLEAN DEFAULT true", updatable = false)
  private boolean isActive;
}