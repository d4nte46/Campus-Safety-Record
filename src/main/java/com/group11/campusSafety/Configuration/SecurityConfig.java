package com.group11.campusSafety.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for frontend SPA applications
                .csrf(csrf -> csrf.disable())

                // Configure headers
                .headers(headers -> headers
                        // Disable Content-Type Options header for troubleshooting
                        .contentTypeOptions(contentTypeOptions -> contentTypeOptions.disable())
                )

                .authorizeHttpRequests(authorize -> authorize
                        // Static resources - allow all access
                        .requestMatchers("/", "/index.html", "/static/**", "/css/**", "/js/**", "/assets/**", "/*.js", "/*.css", "/*.json", "/*.ico").permitAll()

                        // Public API endpoints
                        .requestMatchers("/forgot-password", "/create-account", "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/forgot-password", "/api/create-account", "/api/login").permitAll()

                        // All other endpoints require authentication
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/api/login")
                        .permitAll()
                );

        return http.build();
    }
}
