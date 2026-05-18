package fr.neatcraft.championship.managment.repository.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPeriodException extends RuntimeException {
    public InvalidPeriodException(LocalDate startDate, LocalDate endDate) {
        super("End date %s must be after start date %s".formatted(endDate, startDate));
    }
}
