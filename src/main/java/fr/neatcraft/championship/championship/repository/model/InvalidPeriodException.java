package fr.neatcraft.championship.championship.repository.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPeriodException extends RuntimeException {
    public InvalidPeriodException(LocalDate startDate, LocalDate endDate) {
        super("End date " + endDate + " must be after start date " + startDate);
    }
}
