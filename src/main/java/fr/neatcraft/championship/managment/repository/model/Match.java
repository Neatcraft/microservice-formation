package fr.neatcraft.championship.managment.repository.model;

import java.time.LocalDate;
import java.util.UUID;

public class Match {
    private final UUID id;
    private final UUID championshipId;
    private final String homeTeam;
    private final String awayTeam;
    private final LocalDate scheduledAt;

    private Match(Builder builder) {
        this.id = builder.id != null ? builder.id : UUID.randomUUID();
        this.championshipId = builder.championshipId;
        this.homeTeam = builder.homeTeam;
        this.awayTeam = builder.awayTeam;
        this.scheduledAt = builder.scheduledAt;
    }

    public UUID getId() { return id; }
    public UUID getChampionshipId() { return championshipId; }
    public String getHomeTeam() { return homeTeam; }
    public String getAwayTeam() { return awayTeam; }
    public LocalDate getScheduledAt() { return scheduledAt; }

    public static Builder builder() { return new Builder(); }

    public <T> T to(MatchBuilder<T> visitor) {
        return visitor
                .id(id)
                .championshipId(championshipId)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .scheduledAt(scheduledAt)
                .build();
    }

    public static final class Builder {
        private UUID id;
        private UUID championshipId;
        private String homeTeam;
        private String awayTeam;
        private LocalDate scheduledAt;

        private Builder() {}

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder championshipId(UUID championshipId) { this.championshipId = championshipId; return this; }
        public Builder homeTeam(String homeTeam) { this.homeTeam = homeTeam; return this; }
        public Builder awayTeam(String awayTeam) { this.awayTeam = awayTeam; return this; }
        public Builder scheduledAt(LocalDate scheduledAt) { this.scheduledAt = scheduledAt; return this; }

        public Match build() { return new Match(this); }
    }
}
