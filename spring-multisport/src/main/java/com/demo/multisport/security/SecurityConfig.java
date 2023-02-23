package com.demo.multisport.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf()
                .disable()
                .httpBasic()
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
                    .mvcMatchers("multisport/login", "multisport/register")
                    .authenticated()
                .and()
                .authorizeHttpRequests()
                    .anyRequest()
                    .permitAll()
                .and()
                .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
