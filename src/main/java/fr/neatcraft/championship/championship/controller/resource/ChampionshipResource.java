package fr.neatcraft.championship.championship.controller.resource;

import java.time.LocalDate;
import java.util.UUID;

public class ChampionshipResource {
    private UUID id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public ChampionshipResource() {}

    public ChampionshipResource(UUID id, String name, LocalDate startDate, LocalDate endDate, String status) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
