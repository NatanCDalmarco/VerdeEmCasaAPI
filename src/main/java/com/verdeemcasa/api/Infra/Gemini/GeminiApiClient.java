package com.verdeemcasa.api.Infra.Gemini;

import VerdeEmCasa.Infra.Gemini.DTO.GeminiRequestDTO;
import VerdeEmCasa.Infra.Gemini.DTO.GeminiResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeminiApiClient {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public GeminiResponseDTO translate(String text) {
        String url = apiUrl + "?key=" + apiKey;
        GeminiRequestDTO request = new GeminiRequestDTO(
                "Se esse chamado estiver muito técnico, traduza os termos técnicos para se tornar mais compreensível, " +
                        "se caso estiver muito vago avalie e encontre alguma lógica. " +
                        "Em ambos os casos descreva como proceder para atender esse chamado: " + text);
        return restTemplate.postForObject(url, request, GeminiResponseDTO.class);
    }
}
