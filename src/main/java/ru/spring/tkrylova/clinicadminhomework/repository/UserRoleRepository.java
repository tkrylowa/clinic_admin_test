package ru.spring.tkrylova.clinicadminhomework.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.tkrylova.clinicadminhomework.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    Optional<UserRole> findByRoleType(UserRole.RoleType roleType);
}