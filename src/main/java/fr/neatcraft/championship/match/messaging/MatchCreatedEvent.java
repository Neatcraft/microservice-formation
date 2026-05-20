package fr.neatcraft.championship.match.messaging;

import java.time.LocalDate;
import java.util.UUID;

public record MatchCreatedEvent(
        UUID championshipId,
        String homeTeam,
        String awayTeam,
        LocalDate scheduledAt
) {}
