package fr.neatcraft.championship.championship.controller;

import fr.neatcraft.championship.championship.controller.resource.ChampionshipResource;
import fr.neatcraft.championship.championship.repository.model.ChampionshipBuilder;
import fr.neatcraft.championship.championship.repository.model.ChampionshipStatus;

import java.time.LocalDate;
import java.util.UUID;

public class ChampionshipResourceBuilder implements ChampionshipBuilder<ChampionshipResource> {
    private UUID id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    @Override
    public ChampionshipResourceBuilder id(UUID id) { this.id = id; return this; }

    @Override
    public ChampionshipResourceBuilder name(String name) { this.name = name; return this; }

    @Override
    public ChampionshipResourceBuilder startDate(LocalDate startDate) { this.startDate = startDate; return this; }

    @Override
    public ChampionshipResourceBuilder endDate(LocalDate endDate) { this.endDate = endDate; return this; }

    @Override
    public ChampionshipResourceBuilder status(ChampionshipStatus status) { this.status = status.name(); return this; }

    @Override
    public ChampionshipResource build() {
        return new ChampionshipResource(id, name, startDate, endDate, status);
    }
}
