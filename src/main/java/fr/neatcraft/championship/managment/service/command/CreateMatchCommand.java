package fr.neatcraft.championship.managment.service.command;

import java.time.LocalDate;

public record CreateMatchCommand(String homeTeam, String awayTeam, LocalDate scheduledAt) {}
