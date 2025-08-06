package com.example.lmapp.backendlm; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
           @Override
public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**")
            .allowedOrigins("http://localhost:4200")
            .allowedOrigins("https://taupe-wisp-b654bd.netlify.app")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*");

    registry.addMapping("/files/**")  // 
            .allowedOrigins("http://localhost:4200")
            .allowedMethods("GET")
            .allowedHeaders("*");
}

        };
    }
}
