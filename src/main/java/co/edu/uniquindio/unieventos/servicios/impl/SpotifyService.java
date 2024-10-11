package co.edu.uniquindio.unieventos.servicios.impl;

import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Base64;

@Service
public class SpotifyService {

    private final WebClient webClient;
    private final String clientId;
    private final String clientSecret;

    public SpotifyService(WebClient.Builder webClientBuilder,
                          @Value("${spotify.client-id}") String clientId,
                          @Value("${spotify.client-secret}") String clientSecret) {
        this.webClient = webClientBuilder.build();
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String searchSong(String query) {
        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("La consulta no puede estar vacía.");
        }

        String uri = UriComponentsBuilder
                .fromUriString("https://api.spotify.com/v1/search")
                .queryParam("q", query)
                .queryParam("type", "track")
                .toUriString();

        return this.webClient.get()
                .uri(uri)
                .headers(headers -> headers.setBearerAuth(getSpotifyAccessToken()))
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), clientResponse -> {
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(errorBody -> {
                                throw new RuntimeException("Error al buscar la canción: " + errorBody);
                            });
                })
                .bodyToMono(String.class)
                .block();
    }

    public String getSpotifyAccessToken() { // Método público
        String tokenUri = "https://accounts.spotify.com/api/token";
        String basicAuth = "Basic " + Base64.getEncoder()
                .encodeToString((clientId + ":" + clientSecret).getBytes());

        String tokenResponse = this.webClient.post()
                .uri(tokenUri)
                .header("Authorization", basicAuth)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .bodyValue("grant_type=client_credentials")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JSONObject jsonResponse = new JSONObject(tokenResponse);
        return jsonResponse.getString("access_token");
    }
}
