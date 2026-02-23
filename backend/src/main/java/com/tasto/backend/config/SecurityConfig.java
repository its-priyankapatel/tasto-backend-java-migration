package com.tasto.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/food/add-food").permitAll()
//                        .requestMatchers("/food/get-food").permitAll()
//                        .requestMatchers("/food/update-food").permitAll()
//                        .requestMatchers("/food/delete-food").permitAll()
//                        .requestMatchers("/category/add-category").permitAll()
//                        .requestMatchers("/user/register").permitAll()
//                        .anyRequest().authenticated()
//                );
                .authorizeHttpRequests(auth->auth.anyRequest().permitAll());

        return http.build();
    }
}
