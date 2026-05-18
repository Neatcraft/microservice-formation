package fr.neatcraft.championship.managment.controller.resource;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "action")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChangeDateCommand.class, name = "CHANGE_DATE"),
        @JsonSubTypes.Type(value = ChangeStatusCommand.class, name = "CHANGE_STATUS")
})
public abstract class ChampionshipModificationCommand {
    public abstract void accept(fr.neatcraft.championship.managment.controller.ChampionshipModificationVisitor visitor);
}
