package fr.neatcraft.championship.match.client;

import fr.neatcraft.championship.security.MdcKeys;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

public class MdcCorrelationInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            org.springframework.http.HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        String correlationId = MDC.get(MdcKeys.CORRELATION_ID);
        if (correlationId != null) {
            request.getHeaders().set(MdcKeys.CORRELATION_ID_HEADER, correlationId);
        }

        try {
            var attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            String authorization = attrs.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
            if (authorization != null) {
                request.getHeaders().set(HttpHeaders.AUTHORIZATION, authorization);
            }
        } catch (IllegalStateException ignored) {
            // pas de requête servlet courante (contexte async ou Kafka)
        }

        return execution.execute(request, body);
    }
}
