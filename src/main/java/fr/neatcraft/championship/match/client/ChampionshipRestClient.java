package fr.neatcraft.championship.match.client;

import fr.neatcraft.championship.championship.controller.resource.ChampionshipResource;
import fr.neatcraft.championship.championship.service.ChampionshipNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Component
public class ChampionshipRestClient {

    private final RestClient restClient;

    public ChampionshipRestClient(@Value("${championship.service.url}") String baseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .requestInterceptor(new MdcCorrelationInterceptor())
                .build();
    }

    public ChampionshipResource findById(UUID id) {
        try {
            return restClient.get()
                    .uri("/championship/{id}", id)
                    .retrieve()
                    .body(ChampionshipResource.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ChampionshipNotFoundException(id);
        }
    }
}
