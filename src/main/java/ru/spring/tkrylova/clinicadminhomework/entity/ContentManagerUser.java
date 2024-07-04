package ru.spring.tkrylova.clinicadminhomework.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "content_manager_user")
public class ContentManagerUser {
    @Id
    @GeneratedValue
    private long id;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole userRole;
}