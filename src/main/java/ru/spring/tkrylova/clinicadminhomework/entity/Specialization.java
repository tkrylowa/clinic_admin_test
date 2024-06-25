package ru.spring.tkrylova.clinicadminhomework.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "specialization")
public class Specialization {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", nullable = false)
  @JsonProperty(value = "specialization_name")
  private String name;

  @Column(name = "code_latin", nullable = false, unique = true)
  @JsonProperty(value = "specialization_code")
  private String code;

  //    TEXT
  @Column(name = "description", length = 3000)
  private String description;

  @Column(name = "is_active", insertable = false, columnDefinition = "BOOLEAN DEFAULT true", updatable = false)
  @JsonProperty(value = "enable")
  private boolean isActive;

  @Column(name = "created_at", columnDefinition = "TIMESTAMP", updatable = false)
  private LocalDateTime createdAt;
}