package com.demo.multisport.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class WebConf implements WebMvcConfigurer {


//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedOrigins("http://localhost:4200")
//                .allowedHeaders(CorsConfiguration.ALL)
//                .allowCredentials(true)
//                .exposedHeaders("Origin", "Content-TypeCenter", "Accept", "Authorization",
//                        "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
//
//    }
}
