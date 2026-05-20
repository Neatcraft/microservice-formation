package fr.neatcraft.championship.match.service;

import fr.neatcraft.championship.championship.repository.ChampionshipRepository;
import fr.neatcraft.championship.championship.service.ChampionshipNotFoundException;
import fr.neatcraft.championship.match.messaging.MatchCreatedEvent;
import fr.neatcraft.championship.match.messaging.MatchProducer;
import fr.neatcraft.championship.match.repository.MatchRepository;
import fr.neatcraft.championship.match.repository.model.Match;
import fr.neatcraft.championship.match.service.command.CreateMatchCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class MatchService {
    private final ChampionshipRepository championshipRepository;
    private final MatchRepository matchRepository;
    private final MatchProducer matchProducer;

    public MatchService(ChampionshipRepository championshipRepository, MatchRepository matchRepository, MatchProducer matchProducer) {
        this.championshipRepository = championshipRepository;
        this.matchRepository = matchRepository;
        this.matchProducer = matchProducer;
    }

    public List<Match> findByChampionship(UUID championshipId) {
        return this.matchRepository.findByChampionshipId(championshipId);
    }

    public Match findById(UUID id) {
        return this.matchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFoundException(id));
    }

    @Transactional
    public void create(UUID championshipId, CreateMatchCommand command) {
        championshipRepository.findById(championshipId)
                .orElseThrow(() -> new ChampionshipNotFoundException(championshipId));

        var match = Match.builder()
                .championshipId(championshipId)
                .homeTeam(command.homeTeam())
                .awayTeam(command.awayTeam())
                .scheduledAt(command.scheduledAt())
                .build();
        matchRepository.create(match);

        matchProducer.publish(new MatchCreatedEvent(championshipId, command.homeTeam(), command.awayTeam(), command.scheduledAt()));
    }

    @Transactional
    public void delete(UUID id) {
        this.matchRepository.delete(id);
    }
}
