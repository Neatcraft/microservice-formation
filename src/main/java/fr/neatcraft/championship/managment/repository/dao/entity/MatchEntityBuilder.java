package fr.neatcraft.championship.managment.repository.dao.entity;

import java.time.LocalDate;
import java.util.UUID;

public interface MatchEntityBuilder<T> {
    MatchEntityBuilder<T> id(UUID id);
    MatchEntityBuilder<T> championshipId(UUID championshipId);
    MatchEntityBuilder<T> homeTeam(String homeTeam);
    MatchEntityBuilder<T> awayTeam(String awayTeam);
    MatchEntityBuilder<T> scheduledAt(LocalDate scheduledAt);
    T build();
}
