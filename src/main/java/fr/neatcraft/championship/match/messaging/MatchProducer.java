package fr.neatcraft.championship.match.messaging;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class MatchProducer {
    private static final Logger log = LoggerFactory.getLogger(MatchProducer.class);
    private static final String RESILIENCE_INSTANCE = "kafkaProducer";
    public static final String TOPIC = "match.created";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public MatchProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @CircuitBreaker(name = RESILIENCE_INSTANCE, fallbackMethod = "publishFallback")
    @Bulkhead(name = RESILIENCE_INSTANCE)
    public void publish(MatchCreatedEvent event) {
        try {
            kafkaTemplate.send(TOPIC, event.championshipId().toString(), objectMapper.writeValueAsString(event));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private void publishFallback(MatchCreatedEvent event, Throwable t) {
        log.error("Kafka unavailable, circuit open — event dropped: championshipId={}, cause={}",
                event.championshipId(), t.getMessage());
    }
}
