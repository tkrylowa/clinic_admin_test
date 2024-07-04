package ru.spring.tkrylova.clinicadminhomework.configuration;

import javax.security.auth.login.AccountException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.spring.tkrylova.clinicadminhomework.service.ContentManagerUserDetailService;

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfiguration {

  private final ContentManagerUserDetailService contentManagerUserDetailService;


  public SecurityConfiguration(ContentManagerUserDetailService userDetailService) {
    this.contentManagerUserDetailService = userDetailService;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws AccountException {
    try {
      return config.getAuthenticationManager();
    } catch (Exception e) {
      throw new AccountException("AuthenticationManager not configured: " + e.getMessage());
    }
  }

  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(
        contentManagerUserDetailService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.csrf(AbstractHttpConfigurer::disable)
        .authenticationProvider(authenticationProvider())
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/account/registration", "/account/login")
            .permitAll()
            .anyRequest()
            .authenticated())
        .formLogin(form -> form
            .usernameParameter("application_user_email")
            .passwordParameter("application_user_password")
            .loginPage("/account/login")
            .loginProcessingUrl("/account/login")
            .failureUrl("/account/login?failed")
            .defaultSuccessUrl("/account")
            .permitAll())
        .logout(logout -> logout.logoutUrl(
                "/account/logout")
            .logoutSuccessUrl("/account/login")
            .permitAll())
        .build();
  }

}