package com.example.edufygenre.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// ED-42-AWS
@Configuration
@EnableWebSecurity
public class SecurityConfig {


        //TODO Inte klar
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .headers(h -> h.frameOptions(f -> f.disable()))
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/h2-console/**").permitAll()
                                .anyRequest().permitAll()
                );

        return http.build();
    }

}
