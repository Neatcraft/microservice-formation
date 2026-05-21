package integration.championship;

import fr.neatcraft.championship.ChampionshipApplication;
import fr.neatcraft.championship.championship.controller.resource.ChampionshipResource;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import tools.jackson.databind.ObjectMapper;

import javax.crypto.SecretKey;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ChampionshipApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "spring.kafka.bootstrap-servers=localhost:9092",
        "spring.kafka.consumer.group-id=championship-it"
})
class ChampionshipSecurityTest {

    private static final String JWT_SECRET = "championship-secret-key-must-be-at-least-32-chars";
    private static final SecretKey SIGNING_KEY =
            Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    private final HttpClient http = HttpClient.newHttpClient();

    @Test
    void adminCanCreateChampionship() throws Exception {
        var response = post("/championship", sampleChampionship(), tokenFor("admin-id", "admin", List.of("ADMIN")));

        assertThat(response.statusCode()).isEqualTo(201);
    }

    @Test
    void userWithoutAdminRoleIsRejected() throws Exception {
        var response = post("/championship", sampleChampionship(), tokenFor("user-id", "user", List.of("USER")));

        assertThat(response.statusCode()).isEqualTo(403);
    }

    @Test
    void unauthenticatedRequestIsRejected() throws Exception {
        var body = objectMapper.writeValueAsString(sampleChampionship());
        var request = HttpRequest.newBuilder(uri("/championship"))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();

        var response = http.send(request, HttpResponse.BodyHandlers.discarding());

        assertThat(response.statusCode()).isEqualTo(401);
    }

    @Test
    void adminCanListChampionships() throws Exception {
        var request = HttpRequest.newBuilder(uri("/championship"))
                .GET()
                .header("Authorization", "Bearer " + tokenFor("admin-id", "admin", List.of("ADMIN")))
                .build();

        var response = http.send(request, HttpResponse.BodyHandlers.discarding());

        assertThat(response.statusCode()).isEqualTo(200);
    }

    private HttpResponse<Void> post(String path, Object body, String token) throws Exception {
        var request = HttpRequest.newBuilder(uri(path))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(body)))
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer " + token)
                .build();
        return http.send(request, HttpResponse.BodyHandlers.discarding());
    }

    private URI uri(String path) {
        return URI.create("http://localhost:" + port + path);
    }

    private static String tokenFor(String userId, String username, List<String> roles) {
        return Jwts.builder()
                .subject(userId)
                .claim("username", username)
                .claim("roles", roles)
                .signWith(SIGNING_KEY)
                .compact();
    }

    private static ChampionshipResource sampleChampionship() {
        return new ChampionshipResource(
                null,
                "Ligue 1",
                LocalDate.of(2026, 9, 1),
                LocalDate.of(2027, 5, 31),
                null
        );
    }
}
