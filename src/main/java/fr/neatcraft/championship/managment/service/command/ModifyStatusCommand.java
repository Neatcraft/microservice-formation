package fr.neatcraft.championship.managment.service.command;

import fr.neatcraft.championship.managment.repository.model.ChampionshipStatus;

public record ModifyStatusCommand(ChampionshipStatus status) {}
