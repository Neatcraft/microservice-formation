package fr.neatcraft.championship.championship.controller;

import fr.neatcraft.championship.championship.controller.resource.ChangeDateCommand;
import fr.neatcraft.championship.championship.controller.resource.ChangeStatusCommand;
import fr.neatcraft.championship.championship.controller.resource.ChampionshipModificationCommand;
import fr.neatcraft.championship.championship.controller.resource.ChampionshipResource;
import fr.neatcraft.championship.championship.repository.model.Championship;
import fr.neatcraft.championship.championship.repository.model.ChampionshipStatus;
import fr.neatcraft.championship.championship.service.ChampionshipService;
import fr.neatcraft.championship.championship.service.command.ModifyDatesCommand;
import fr.neatcraft.championship.championship.service.command.ModifyStatusCommand;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/championship")
public class ChampionshipController {
    private final ChampionshipService championshipService;

    public ChampionshipController(ChampionshipService championshipService) {
        this.championshipService = championshipService;
    }

    @GetMapping
    public List<ChampionshipResource> findAll() {
        return this.championshipService.findAll()
                .stream()
                .map(championship -> championship.to(new ChampionshipResourceBuilder()))
                .toList();
    }

    @GetMapping("/{id}")
    ChampionshipResource findOneById(@PathVariable UUID id) {
        return this.championshipService.findOneById(id)
                .to(new ChampionshipResourceBuilder());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@RequestBody ChampionshipResource resource) {
        var championship = Championship.builder()
                .name(resource.getName())
                .startDate(resource.getStartDate())
                .endDate(resource.getEndDate())
                .build();
        this.championshipService.create(championship);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    void replace(@PathVariable UUID id, @RequestBody ChampionshipResource resource) {
        var championship = Championship.builder()
                .id(id)
                .name(resource.getName())
                .startDate(resource.getStartDate())
                .endDate(resource.getEndDate())
                .status(resource.getStatus() != null ? ChampionshipStatus.valueOf(resource.getStatus()) : ChampionshipStatus.PLANNED)
                .build();
        this.championshipService.replace(id, championship);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{id}")
    void modify(@PathVariable UUID id, @RequestBody ChampionshipModificationCommand command) {
        command.accept(new ChampionshipModificationVisitor() {
            @Override
            public void visit(ChangeDateCommand cmd) {
                championshipService.modify(id, new ModifyDatesCommand(cmd.getStartDate(), cmd.getEndDate()));
            }

            @Override
            public void visit(ChangeStatusCommand cmd) {
                championshipService.modify(id, new ModifyStatusCommand(cmd.getStatus()));
            }
        });
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    void delete(@PathVariable UUID id) {
        this.championshipService.delete(id);
    }
}
