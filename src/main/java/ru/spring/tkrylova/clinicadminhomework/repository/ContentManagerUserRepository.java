package ru.spring.tkrylova.clinicadminhomework.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.tkrylova.clinicadminhomework.entity.ContentManagerUser;

public interface ContentManagerUserRepository extends JpaRepository<ContentManagerUser, Long> {
    Optional<ContentManagerUser> findByEmail(String email);
    boolean existsByEmail(String email);
}