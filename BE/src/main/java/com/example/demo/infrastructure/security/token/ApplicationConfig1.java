package com.example.demo.infrastructure.security.token;

import com.example.demo.entities.Admin;
import com.example.demo.entities.OwnerHomestay;
import com.example.demo.entities.User;
import com.example.demo.infrastructure.security.auth.ApplicationAuditAware;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.OwnerHomestayRepository;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig1 {

    private final UserRepository userRepository;

    private final OwnerHomestayRepository ownerHomestayRepository;

    private final AdminRepository adminRepository;

    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            Optional<OwnerHomestay> ownerHomestay = ownerHomestayRepository.findByUsername(username);
            if (ownerHomestay.isPresent()) {
                return ownerHomestay.get();
            }
            Optional<User> user = userRepository.findByUsername(username);
            if (user.isPresent()) {
                return user.get();
            }
            Optional<Admin> admin = adminRepository.findByUsername(username);
            if (admin.isPresent()) {
                return admin.get();
            }

            throw new UsernameNotFoundException("User not found");
        };
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return new ApplicationAuditAware();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
