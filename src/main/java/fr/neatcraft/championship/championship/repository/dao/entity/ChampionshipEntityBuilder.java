package fr.neatcraft.championship.championship.repository.dao.entity;

import java.time.LocalDate;
import java.util.UUID;

public interface ChampionshipEntityBuilder<T> {
    ChampionshipEntityBuilder<T> id(UUID id);
    ChampionshipEntityBuilder<T> name(String name);
    ChampionshipEntityBuilder<T> startDate(LocalDate startDate);
    ChampionshipEntityBuilder<T> endDate(LocalDate endDate);
    ChampionshipEntityBuilder<T> status(String status);
    T build();
}
