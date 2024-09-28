package dbp.hackathon.QRapi.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class QRapiService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<String> getQR() {
        return webClientBuilder.baseUrl("http://api.qrserver.com")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("data", "HelloWorld!")
                        .queryParam("size", "100x100")
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }
}
