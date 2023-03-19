package com.demo.multisport.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors()
                .and()
                .csrf()
                .disable()
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                    .mvcMatchers(HttpMethod.POST, "multisport")
                    .permitAll()
                .and()
                .authorizeHttpRequests()
                    .mvcMatchers(HttpMethod.POST, "multisport/newuser")
                    .permitAll()
                .and()
                .authorizeHttpRequests()
                    .mvcMatchers(HttpMethod.POST, "multisport/login")
                    .authenticated()
                .and()
                .authorizeHttpRequests()
                    .mvcMatchers("multisport/admin/**")
                    .hasAuthority("ADMIN")
                .and()
                .authorizeHttpRequests()
                    .mvcMatchers(HttpMethod.POST, "multisport/**")
                    .authenticated()
                .and()
                .authorizeHttpRequests()
                    .anyRequest()
                    .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedOrigins(List.of("http://localhost:4200"));
        cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        cors.setAllowedHeaders(List.of(CorsConfiguration.ALL));
        cors.setAllowCredentials(true);
        cors.setExposedHeaders(List.of("Origin", "Content-TypeCenter", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        UrlBasedCorsConfigurationSource url = new UrlBasedCorsConfigurationSource();
        url.registerCorsConfiguration("/**", cors);
        return url;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
