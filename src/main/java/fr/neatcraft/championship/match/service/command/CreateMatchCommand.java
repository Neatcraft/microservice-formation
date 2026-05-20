package fr.neatcraft.championship.match.service.command;

import java.time.LocalDate;

public record CreateMatchCommand(String homeTeam, String awayTeam, LocalDate scheduledAt) {}
