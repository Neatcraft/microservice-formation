package fr.neatcraft.championship.managment.controller.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.neatcraft.championship.managment.controller.ChampionshipModificationVisitor;

import java.time.LocalDate;

public class ChangeDateCommand extends ChampionshipModificationCommand {
    private final LocalDate startDate;
    private final LocalDate endDate;

    @JsonCreator
    public ChangeDateCommand(
            @JsonProperty("startDate") LocalDate startDate,
            @JsonProperty("endDate") LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }

    @Override
    public void accept(ChampionshipModificationVisitor visitor) {
        visitor.visit(this);
    }
}
