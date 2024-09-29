package dbp.hackathon.QRapi.domain;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class QRapiService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<String> getQR(Long id) {
        return webClientBuilder.baseUrl("http://api.qrserver.com")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/create-qr-code/")
                        .queryParam("data", id)
                        .queryParam("size", "100x100")
                        .build())
                .retrieve()
                .bodyToMono(byte[].class)
                .map(bytes -> Base64.getEncoder().encodeToString(bytes)); // Convertir a Base64
    }
}
