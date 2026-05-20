package fr.neatcraft.championship.championship.controller.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.neatcraft.championship.championship.controller.ChampionshipModificationVisitor;
import fr.neatcraft.championship.championship.repository.model.ChampionshipStatus;

public class ChangeStatusCommand extends ChampionshipModificationCommand {
    private final ChampionshipStatus status;

    @JsonCreator
    public ChangeStatusCommand(@JsonProperty("status") ChampionshipStatus status) {
        this.status = status;
    }

    public ChampionshipStatus getStatus() { return status; }

    @Override
    public void accept(ChampionshipModificationVisitor visitor) {
        visitor.visit(this);
    }
}
