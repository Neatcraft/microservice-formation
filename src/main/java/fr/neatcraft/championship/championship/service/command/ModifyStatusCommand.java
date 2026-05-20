package fr.neatcraft.championship.championship.service.command;

import fr.neatcraft.championship.championship.repository.model.ChampionshipStatus;

public record ModifyStatusCommand(ChampionshipStatus status) {}
