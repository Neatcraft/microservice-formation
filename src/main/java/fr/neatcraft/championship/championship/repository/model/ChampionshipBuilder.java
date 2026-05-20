package fr.neatcraft.championship.championship.repository.model;

import java.time.LocalDate;
import java.util.UUID;

public interface ChampionshipBuilder<T> {
    ChampionshipBuilder<T> id(UUID id);
    ChampionshipBuilder<T> name(String name);
    ChampionshipBuilder<T> startDate(LocalDate startDate);
    ChampionshipBuilder<T> endDate(LocalDate endDate);
    ChampionshipBuilder<T> status(ChampionshipStatus status);
    T build();
}
