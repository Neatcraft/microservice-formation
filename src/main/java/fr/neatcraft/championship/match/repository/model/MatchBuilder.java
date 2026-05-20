package fr.neatcraft.championship.match.repository.model;

import java.time.LocalDate;
import java.util.UUID;

public interface MatchBuilder<T> {
    MatchBuilder<T> id(UUID id);
    MatchBuilder<T> championshipId(UUID championshipId);
    MatchBuilder<T> homeTeam(String homeTeam);
    MatchBuilder<T> awayTeam(String awayTeam);
    MatchBuilder<T> scheduledAt(LocalDate scheduledAt);
    T build();
}
