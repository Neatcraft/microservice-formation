package fr.neatcraft.championship.championship.service.command;

import java.time.LocalDate;

public record ModifyDatesCommand(LocalDate startDate, LocalDate endDate) {}
