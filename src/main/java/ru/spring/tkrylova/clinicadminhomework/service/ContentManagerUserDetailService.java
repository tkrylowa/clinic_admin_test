package ru.spring.tkrylova.clinicadminhomework.service;

import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.spring.tkrylova.clinicadminhomework.entity.ContentManagerUser;
import ru.spring.tkrylova.clinicadminhomework.repository.ContentManagerUserRepository;

@Service
@Slf4j
public class ContentManagerUserDetailService implements UserDetailsService {
    private final ContentManagerUserRepository contentManagerUserRepository;

    public ContentManagerUserDetailService(ContentManagerUserRepository contentManagerUserRepository) {
        this.contentManagerUserRepository = contentManagerUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ContentManagerUser applicationUser = contentManagerUserRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("Content manager not found"));
        GrantedAuthority authority = new SimpleGrantedAuthority(
                applicationUser.getUserRole().getRoleType().name());
        log.info(authority.getAuthority());
        return new User(email, applicationUser.getPassword(), Set.of(authority));
    }
}