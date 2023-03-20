package com.example.backspringboot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable()
            //---------------------Non secure routes----------------
            .authorizeHttpRequests()
            .requestMatchers("/api/auth/**", "/static/images/**")
            .permitAll()

            //---------------------Non secure routes only GET method----------------
            .and()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET, "/api/property/**")
            .permitAll()

            //---------------------Secured by role OWNER----------------
            .and()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST, "/api/property")
            .hasAuthority("OWNER")

            //---------------------Secured by user----------------
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
}
