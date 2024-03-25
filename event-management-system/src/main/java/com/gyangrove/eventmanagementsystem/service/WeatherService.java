package com.gyangrove.eventmanagementsystem.service;

import com.gyangrove.eventmanagementsystem.dto.WeatherDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;

@Service
public class WeatherService {
    private WebClient webClient;
    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://gg-backend-assignment.azurewebsites.net/api").build();
    }
    public WeatherDto getWeather(String city, LocalDate date) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/Weather")
                        .queryParam("city", city)
                        .queryParam("date", date)
                        .queryParam("code", "KfQnTWHJbg1giyB_Q9Ih3Xu3L9QOBDTuU5zwqVikZepCAzFut3rqsg==")
                        .build())
                .retrieve()
                .bodyToMono(WeatherDto.class)
                .block();
    }
}
