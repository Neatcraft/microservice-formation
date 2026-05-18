package fr.neatcraft.championship.managment.repository.model;

import fr.neatcraft.championship.managment.repository.dao.entity.ChampionshipEntityBuilder;

import java.time.LocalDate;
import java.util.UUID;

public class ChampionshipAggregateBuilder implements ChampionshipEntityBuilder<Championship> {
    private UUID id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    @Override
    public ChampionshipAggregateBuilder id(UUID id) { this.id = id; return this; }

    @Override
    public ChampionshipAggregateBuilder name(String name) { this.name = name; return this; }

    @Override
    public ChampionshipAggregateBuilder startDate(LocalDate startDate) { this.startDate = startDate; return this; }

    @Override
    public ChampionshipAggregateBuilder endDate(LocalDate endDate) { this.endDate = endDate; return this; }

    @Override
    public ChampionshipAggregateBuilder status(String status) { this.status = status; return this; }

    @Override
    public Championship build() {
        return Championship.builder()
                .id(id)
                .name(name)
                .startDate(startDate)
                .endDate(endDate)
                .status(status != null ? ChampionshipStatus.valueOf(status) : ChampionshipStatus.PLANNED)
                .build();
    }
}
