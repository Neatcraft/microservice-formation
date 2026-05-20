package fr.neatcraft.championship.championship.controller;

import fr.neatcraft.championship.championship.controller.resource.ChangeDateCommand;
import fr.neatcraft.championship.championship.controller.resource.ChangeStatusCommand;

public interface ChampionshipModificationVisitor {
    void visit(ChangeDateCommand cmd);
    void visit(ChangeStatusCommand cmd);
}
