package com.verdeemcasa.api.Infra.Configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a configuração a todos os endpoints da API
                .allowedOrigins("http://localhost:3000") // Permite requisições vindas desta origem
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite estes métodos HTTP
                .allowedHeaders("*"); // Permite todos os cabeçalhos na requisição
    }
}