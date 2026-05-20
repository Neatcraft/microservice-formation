package fr.neatcraft.championship.match.repository.model;

import fr.neatcraft.championship.match.repository.dao.entity.MatchEntityBuilder;

import java.time.LocalDate;
import java.util.UUID;

public class MatchAggregateBuilder implements MatchEntityBuilder<Match> {
    private UUID id;
    private UUID championshipId;
    private String homeTeam;
    private String awayTeam;
    private LocalDate scheduledAt;

    @Override
    public MatchAggregateBuilder id(UUID id) { this.id = id; return this; }

    @Override
    public MatchAggregateBuilder championshipId(UUID championshipId) { this.championshipId = championshipId; return this; }

    @Override
    public MatchAggregateBuilder homeTeam(String homeTeam) { this.homeTeam = homeTeam; return this; }

    @Override
    public MatchAggregateBuilder awayTeam(String awayTeam) { this.awayTeam = awayTeam; return this; }

    @Override
    public MatchAggregateBuilder scheduledAt(LocalDate scheduledAt) { this.scheduledAt = scheduledAt; return this; }

    @Override
    public Match build() {
        return Match.builder()
                .id(id)
                .championshipId(championshipId)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .scheduledAt(scheduledAt)
                .build();
    }
}
