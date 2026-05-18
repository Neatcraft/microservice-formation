package fr.neatcraft.championship.managment.service.command;

import java.time.LocalDate;

public record ModifyDatesCommand(LocalDate startDate, LocalDate endDate) {}
