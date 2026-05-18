package fr.neatcraft.championship.managment.controller.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.neatcraft.championship.managment.controller.ChampionshipModificationVisitor;
import fr.neatcraft.championship.managment.repository.model.ChampionshipStatus;

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
