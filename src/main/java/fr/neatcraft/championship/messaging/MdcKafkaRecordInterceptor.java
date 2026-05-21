package fr.neatcraft.championship.messaging;

import fr.neatcraft.championship.security.MdcKeys;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.slf4j.MDC;
import org.springframework.kafka.listener.RecordInterceptor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * Restores MDC context from Kafka message headers before the listener is invoked,
 * and clears it afterwards to avoid leaking context between messages.
 */
@Component
public class MdcKafkaRecordInterceptor implements RecordInterceptor<String, String> {

    @Override
    public ConsumerRecord<String, String> intercept(ConsumerRecord<String, String> record,
                                                     Consumer<String, String> consumer) {
        restoreFromHeader(record, MdcKeys.CORRELATION_ID);
        restoreFromHeader(record, MdcKeys.USER_ID);
        restoreFromHeader(record, MdcKeys.USERNAME);
        return record;
    }

    @Override
    public void success(ConsumerRecord<String, String> record, Consumer<String, String> consumer) {
        MDC.clear();
    }

    @Override
    public void failure(ConsumerRecord<String, String> record, Exception exception,
                        Consumer<String, String> consumer) {
        MDC.clear();
    }

    private void restoreFromHeader(ConsumerRecord<String, String> record, String key) {
        Header header = record.headers().lastHeader(key);
        if (header != null) {
            MDC.put(key, new String(header.value(), StandardCharsets.UTF_8));
        }
    }
}
