package fr.neatcraft.championship.match.controller;

import fr.neatcraft.championship.match.controller.resource.MatchResource;
import fr.neatcraft.championship.match.repository.model.MatchBuilder;

import java.time.LocalDate;
import java.util.UUID;

public class MatchResourceBuilder implements MatchBuilder<MatchResource> {
    private UUID id;
    private UUID championshipId;
    private String homeTeam;
    private String awayTeam;
    private LocalDate scheduledAt;

    @Override
    public MatchResourceBuilder id(UUID id) { this.id = id; return this; }

    @Override
    public MatchResourceBuilder championshipId(UUID championshipId) { this.championshipId = championshipId; return this; }

    @Override
    public MatchResourceBuilder homeTeam(String homeTeam) { this.homeTeam = homeTeam; return this; }

    @Override
    public MatchResourceBuilder awayTeam(String awayTeam) { this.awayTeam = awayTeam; return this; }

    @Override
    public MatchResourceBuilder scheduledAt(LocalDate scheduledAt) { this.scheduledAt = scheduledAt; return this; }

    @Override
    public MatchResource build() {
        return new MatchResource(id, championshipId, homeTeam, awayTeam, scheduledAt);
    }
}
