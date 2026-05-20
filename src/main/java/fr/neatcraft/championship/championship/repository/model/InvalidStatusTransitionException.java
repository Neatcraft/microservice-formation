package fr.neatcraft.championship.championship.repository.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidStatusTransitionException extends RuntimeException {
    public InvalidStatusTransitionException(ChampionshipStatus from, ChampionshipStatus to) {
        super("Cannot transition from " + from + " to " + to);
    }
}
