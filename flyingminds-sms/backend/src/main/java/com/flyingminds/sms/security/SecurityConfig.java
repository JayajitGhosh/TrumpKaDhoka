package com.flyingminds.sms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * HTTP security configuration.
 * Currently permissive for scaffolding; to be tightened with JWT auth and role-based access rules.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**", "/actuator/**").permitAll()
                .anyRequest().permitAll() // TODO: tighten with JWT & RBAC
            )
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}