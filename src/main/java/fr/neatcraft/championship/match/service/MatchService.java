package fr.neatcraft.championship.match.service;

import fr.neatcraft.championship.match.client.ChampionshipRestClient;
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
    private final ChampionshipRestClient championshipRestClient;
    private final MatchRepository matchRepository;
    private final MatchProducer matchProducer;

    public MatchService(ChampionshipRestClient championshipRestClient, MatchRepository matchRepository, MatchProducer matchProducer) {
        this.championshipRestClient = championshipRestClient;
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
        championshipRestClient.findById(championshipId);

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
