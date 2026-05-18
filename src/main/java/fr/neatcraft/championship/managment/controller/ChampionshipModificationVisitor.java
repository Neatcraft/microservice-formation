package fr.neatcraft.championship.managment.controller;

import fr.neatcraft.championship.managment.controller.resource.ChangeDateCommand;
import fr.neatcraft.championship.managment.controller.resource.ChangeStatusCommand;

public interface ChampionshipModificationVisitor {
    void visit(ChangeDateCommand command);
    void visit(ChangeStatusCommand command);
}
