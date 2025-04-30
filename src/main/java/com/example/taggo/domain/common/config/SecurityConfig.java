package com.example.taggo.domain.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // REST API에서 보통 꺼둠
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/user/register",
                                "/api/v1/user/login"
                        ).permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }

}
