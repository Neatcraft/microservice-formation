package fr.neatcraft.championship.match.repository.dao.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "matches")
public class MatchEntity {
    @Id
    private final String id;
    private final String championshipId;
    private final String homeTeam;
    private final String awayTeam;
    private final LocalDate scheduledAt;

    @PersistenceCreator
    MatchEntity(String id, String championshipId, String homeTeam, String awayTeam, LocalDate scheduledAt) {
        this.id = id;
        this.championshipId = championshipId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.scheduledAt = scheduledAt;
    }

    public static Builder builder() { return new Builder(); }

    public <T> T to(MatchEntityBuilder<T> visitor) {
        return visitor
                .id(UUID.fromString(this.id))
                .championshipId(UUID.fromString(this.championshipId))
                .homeTeam(this.homeTeam)
                .awayTeam(this.awayTeam)
                .scheduledAt(this.scheduledAt)
                .build();
    }

    public static final class Builder {
        private String id;
        private String championshipId;
        private String homeTeam;
        private String awayTeam;
        private LocalDate scheduledAt;

        private Builder() {}

        public Builder id(String id) { this.id = id; return this; }
        public Builder championshipId(String championshipId) { this.championshipId = championshipId; return this; }
        public Builder homeTeam(String homeTeam) { this.homeTeam = homeTeam; return this; }
        public Builder awayTeam(String awayTeam) { this.awayTeam = awayTeam; return this; }
        public Builder scheduledAt(LocalDate scheduledAt) { this.scheduledAt = scheduledAt; return this; }

        public MatchEntity build() {
            return new MatchEntity(id, championshipId, homeTeam, awayTeam, scheduledAt);
        }
    }
}
