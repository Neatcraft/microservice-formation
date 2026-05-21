package fr.neatcraft.championship.messaging;

import fr.neatcraft.championship.security.MdcKeys;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.MDC;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Copies MDC values (correlationId, userId, username) into Kafka message headers
 * before each send, enabling context propagation across service boundaries.
 */
public class MdcKafkaProducerInterceptor implements ProducerInterceptor<String, String> {

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        addHeader(record, MdcKeys.CORRELATION_ID);
        addHeader(record, MdcKeys.USER_ID);
        addHeader(record, MdcKeys.USERNAME);
        return record;
    }

    private void addHeader(ProducerRecord<String, String> record, String key) {
        String value = MDC.get(key);
        if (value != null) {
            record.headers().add(key, value.getBytes(StandardCharsets.UTF_8));
        }
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {}

    @Override
    public void close() {}

    @Override
    public void configure(Map<String, ?> configs) {}
}
