package dbp.hackathon.QRapi.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class QRapiService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<String> getQR(Long id) {
        return webClientBuilder.baseUrl("http://api.qrserver.com/v1/create-qr-code/")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("data", id)
                        .queryParam("size", "100x100")
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }
}
