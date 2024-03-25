package com.gyangrove.eventmanagementsystem.service;

import com.gyangrove.eventmanagementsystem.dto.DistanceDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DistanceCalculationService {
    private WebClient webClient;
    public DistanceCalculationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://gg-backend-assignment.azurewebsites.net/api").build();
    }

    public double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/Distance")
                        .queryParam("latitude1", latitude1)
                        .queryParam("longitude1", longitude1)
                        .queryParam("latitude2", latitude2)
                        .queryParam("longitude2", longitude2)
                        .queryParam("code", "IAKvV2EvJa6Z6dEIUqqd7yGAu7IZ8gaH-a0QO6btjRc1AzFu8Y3IcQ==")
                        .build())
                .retrieve()
                .bodyToMono(DistanceDto.class)
                .block()
                .getDistance();
    }
}
