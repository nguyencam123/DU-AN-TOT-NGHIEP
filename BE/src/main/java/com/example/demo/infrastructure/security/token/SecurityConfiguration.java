package com.example.demo.infrastructure.security.token;

import com.example.demo.infrastructure.contant.role.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    private static final String[] WHITE_LIST_URL=
            {
                "/api/v2/login/**",
                "/api/v1/login/**",
                "/api/v3/login/**"
            };
    private static final String[] OWNER_ROLE=
            {
                    "/api/v2/homestay/**",
                    "/api/v2/booking/**",
                    "/api/v2/convenient/**",
                    "/api/v2/convenient/**",
                    "/api/v2/img",
                    "/api/v2/owner/**",
                    "/api/v2/change-pass/**",
                    "/api/v2/promotion/**",
                    "/api/v2/statictical/**"
            };
    private static final String[] ADMIN_ROLE=
            {
                    "/api/v3/booking/**",
                    "/api/v3/homestay/**",
                    "/api/v3/convenient-homestay/**",
                    "/api/v3/change-pass/**",
                    "/api/v3/promotion/**",
                    "/api/v3/statistical/**",
                    "/api/v3/information/**"
            };
    private static final String[] CUSTOMER_ROLE=
            {
                    "/api/v1/booking/**",
                    "/api/v1/cart/**",
                    "/api/v1/comment/**",
                    "/api/v1/convenient-homestay/**",
                    "/api/v1/customer/**",
                    "/api/v1/homestay/**",
                    "/api/v1/homestay",
                    "/api/v1/img-comment/**",
                    "/api/v1/img-homestay/**",
                    "/api/v1/change-pass/**",
                    "/api/v1/payment/**"
            };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(WHITE_LIST_URL).permitAll()
                .requestMatchers(HttpMethod.GET,CUSTOMER_ROLE).permitAll()
                .requestMatchers(OWNER_ROLE).hasAnyRole(RoleOwner.OWNER.name())
                .requestMatchers(HttpMethod.GET,OWNER_ROLE).hasAnyAuthority(PermissionOwner.OWNER_READ.name())
                .requestMatchers(HttpMethod.POST,OWNER_ROLE).hasAnyAuthority(PermissionOwner.OWNER_CREATE.name())
                .requestMatchers(HttpMethod.PUT,OWNER_ROLE).hasAnyAuthority(PermissionOwner.OWNER_UPDATE.name())
                .requestMatchers(ADMIN_ROLE).hasAnyRole(RoleAdmin.ADMIN.name())
                .requestMatchers(HttpMethod.GET,ADMIN_ROLE).hasAnyAuthority(PermissionAdmin.ADMIN_READ.name())
                .requestMatchers(HttpMethod.POST,ADMIN_ROLE).hasAnyAuthority(PermissionAdmin.ADMIN_CREATE.name())
                .requestMatchers(HttpMethod.PUT,ADMIN_ROLE).hasAnyAuthority(PermissionAdmin.ADMIN_UPDATE.name())
                .requestMatchers(CUSTOMER_ROLE).hasAnyRole(RoleCustomer.CUSTOMER.name())
                .requestMatchers(HttpMethod.POST,CUSTOMER_ROLE).hasAnyAuthority(PermissionCustomer.CUSTOMER_CREATE.name())
                .requestMatchers(HttpMethod.PUT,CUSTOMER_ROLE).hasAnyAuthority(PermissionCustomer.CUSTOMER_UPDATE.name())
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}

