package fr.neatcraft.championship.managment.service;

import fr.neatcraft.championship.managment.repository.ChampionshipRepository;
import fr.neatcraft.championship.managment.repository.MatchRepository;
import fr.neatcraft.championship.managment.repository.model.Championship;
import fr.neatcraft.championship.managment.repository.model.Match;
import fr.neatcraft.championship.managment.service.command.CreateMatchCommand;
import fr.neatcraft.championship.managment.service.command.ModifyDatesCommand;
import fr.neatcraft.championship.managment.service.command.ModifyStatusCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ChampionshipService {
    private final ChampionshipRepository championshipRepository;
    private final MatchRepository matchRepository;

    public ChampionshipService(ChampionshipRepository championshipRepository, MatchRepository matchRepository) {
        this.championshipRepository = championshipRepository;
        this.matchRepository = matchRepository;
    }

    public List<Championship> findAll() {
        return this.championshipRepository.findAll();
    }

    public Championship findOneById(UUID id) {
        return this.championshipRepository.findById(id)
                .orElseThrow(() -> new ChampionshipNotFoundException(id));
    }

    @Transactional
    public void create(Championship championship) {
        this.championshipRepository.create(championship);
    }

    @Transactional
    public void replace(UUID id, Championship championship) {
        if (this.championshipRepository.findById(id).isEmpty()) {
            throw new ChampionshipNotFoundException(id);
        }
        this.championshipRepository.replace(championship);
    }

    @Transactional
    public void modify(UUID id, ModifyDatesCommand command) {
        Championship updated = findOneById(id).changePeriod(command.startDate(), command.endDate());
        this.championshipRepository.replace(updated);
    }

    @Transactional
    public void modify(UUID id, ModifyStatusCommand command) {
        Championship updated = findOneById(id).changeStatus(command.status());
        this.championshipRepository.replace(updated);
    }

    @Transactional
    public void addMatch(UUID championshipId, CreateMatchCommand command) {
        findOneById(championshipId);
        var match = Match.builder()
                .championshipId(championshipId)
                .homeTeam(command.homeTeam())
                .awayTeam(command.awayTeam())
                .scheduledAt(command.scheduledAt())
                .build();
        this.matchRepository.create(match);
    }

    @Transactional
    public void delete(UUID id) {
        this.championshipRepository.delete(id);
    }
}
