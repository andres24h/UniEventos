package co.edu.uniquindio.unieventos.controladores;

import co.edu.uniquindio.unieventos.servicios.impl.SpotifyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/spotify")
public class SpotifyController {

    private final SpotifyService spotifyService;

    public SpotifyController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping("/token")  // Endpoint para obtener el token
    public ResponseEntity<String> getToken() {
        try {
            String token = spotifyService.getSpotifyAccessToken(); // Método para obtener el token
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al obtener el token: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchSong(@RequestParam String query) {
        try {
            String response = spotifyService.searchSong(query);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al buscar la canción: " + e.getMessage());
        }
    }
}

