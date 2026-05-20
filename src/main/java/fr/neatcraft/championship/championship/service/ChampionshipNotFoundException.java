package fr.neatcraft.championship.championship.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ChampionshipNotFoundException extends RuntimeException {
    public ChampionshipNotFoundException(UUID id) {
        super("Championship not found: " + id);
    }
}
