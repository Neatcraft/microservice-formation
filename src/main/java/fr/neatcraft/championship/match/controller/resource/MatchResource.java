package fr.neatcraft.championship.match.controller.resource;

import java.time.LocalDate;
import java.util.UUID;

public class MatchResource {
    private UUID id;
    private UUID championshipId;
    private String homeTeam;
    private String awayTeam;
    private LocalDate scheduledAt;

    public MatchResource() {}

    public MatchResource(UUID id, UUID championshipId, String homeTeam, String awayTeam, LocalDate scheduledAt) {
        this.id = id;
        this.championshipId = championshipId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.scheduledAt = scheduledAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getChampionshipId() { return championshipId; }
    public void setChampionshipId(UUID championshipId) { this.championshipId = championshipId; }
    public String getHomeTeam() { return homeTeam; }
    public void setHomeTeam(String homeTeam) { this.homeTeam = homeTeam; }
    public String getAwayTeam() { return awayTeam; }
    public void setAwayTeam(String awayTeam) { this.awayTeam = awayTeam; }
    public LocalDate getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(LocalDate scheduledAt) { this.scheduledAt = scheduledAt; }
}
