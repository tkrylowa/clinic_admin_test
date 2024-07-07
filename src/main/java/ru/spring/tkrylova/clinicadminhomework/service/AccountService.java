package ru.spring.tkrylova.clinicadminhomework.service;

import javax.security.auth.login.AccountException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.spring.tkrylova.clinicadminhomework.entity.ContentManagerUser;
import ru.spring.tkrylova.clinicadminhomework.entity.UserRole;
import ru.spring.tkrylova.clinicadminhomework.entity.UserRole.RoleType;
import ru.spring.tkrylova.clinicadminhomework.repository.ContentManagerUserRepository;
import ru.spring.tkrylova.clinicadminhomework.repository.UserRoleRepository;

@Service
public class AccountService {
    private final ContentManagerUserRepository contentManagerUserRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(ContentManagerUserRepository contentManagerUserRepository,
                          UserRoleRepository userRoleRepository,
                          PasswordEncoder passwordEncoder) {
        this.contentManagerUserRepository = contentManagerUserRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registration(ContentManagerUser user) throws AccountException {
        if (contentManagerUserRepository.existsByEmail(user.getEmail())) {
            throw new AccountException("Email is already taken");
        }
        userRoleRepository.findByRoleType(RoleType.ROLE_MODERATOR_USER)
                .ifPresentOrElse(user::setUserRole,
                        () -> {
                            UserRole userRole = new UserRole();
                            userRole.setRoleType(UserRole.RoleType.ROLE_MODERATOR_USER);
                            user.setUserRole(userRole);
                            userRoleRepository.save(userRole);
                        }
                );
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        contentManagerUserRepository.save(user);
    }
}