package com.tasto.backend.config;

import com.tasto.backend.service.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtService jwtService) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/user/**",
                                "/restaurant/login",
                                "/restaurant/register"
                        ).permitAll()
                        .requestMatchers("/category/**").hasRole("user")
                        .requestMatchers("/food/add-food").hasRole("restaurant")
                        .requestMatchers("/restaurant/deactivate").hasRole("restaurant")
                        .requestMatchers("/restaurant/get-restaurant").hasRole("user")
                        .requestMatchers("/food/get-food").hasRole("user")
                        .requestMatchers("/food/update-food").hasRole("restaurant")
                        .requestMatchers("/food/delete-food").hasRole("restaurant")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthFilter(jwtService),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
