package fr.neatcraft.championship.championship.messaging;

import fr.neatcraft.championship.championship.service.ChampionshipService;
import fr.neatcraft.championship.match.messaging.MatchCreatedEvent;
import fr.neatcraft.championship.match.messaging.MatchProducer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class MatchConsumer {
    private final ChampionshipService championshipService;
    private final ObjectMapper objectMapper;

    public MatchConsumer(ChampionshipService championshipService, ObjectMapper objectMapper) {
        this.championshipService = championshipService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = MatchProducer.TOPIC)
    public void onMatchCreated(String payload) {
        try {
            MatchCreatedEvent event = objectMapper.readValue(payload, MatchCreatedEvent.class);
            championshipService.onMatchAdded(event.championshipId());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
