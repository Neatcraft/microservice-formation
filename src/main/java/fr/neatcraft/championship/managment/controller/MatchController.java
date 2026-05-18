package fr.neatcraft.championship.managment.controller;

import fr.neatcraft.championship.managment.controller.resource.MatchResource;
import fr.neatcraft.championship.managment.service.ChampionshipService;
import fr.neatcraft.championship.managment.service.MatchService;
import fr.neatcraft.championship.managment.service.command.CreateMatchCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/championship/{championshipId}/match")
public class MatchController {
    private final ChampionshipService championshipService;
    private final MatchService matchService;

    public MatchController(ChampionshipService championshipService, MatchService matchService) {
        this.championshipService = championshipService;
        this.matchService = matchService;
    }

    @GetMapping
    List<MatchResource> findByChampionship(@PathVariable UUID championshipId) {
        return this.matchService.findByChampionship(championshipId)
                .stream()
                .map(match -> match.to(new MatchResourceBuilder()))
                .toList();
    }

    @GetMapping("/{id}")
    MatchResource findById(@PathVariable UUID championshipId, @PathVariable UUID id) {
        return this.matchService.findById(id)
                .to(new MatchResourceBuilder());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@PathVariable UUID championshipId, @RequestBody MatchResource resource) {
        this.championshipService.addMatch(championshipId,
                new CreateMatchCommand(resource.getHomeTeam(), resource.getAwayTeam(), resource.getScheduledAt()));
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    void delete(@PathVariable UUID championshipId, @PathVariable UUID id) {
        this.matchService.delete(id);
    }
}
