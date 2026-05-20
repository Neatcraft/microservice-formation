package fr.neatcraft.championship.match.repository;

import fr.neatcraft.championship.match.repository.dao.MatchDAO;
import fr.neatcraft.championship.match.repository.dao.entity.MatchEntity;
import fr.neatcraft.championship.match.repository.model.Match;
import fr.neatcraft.championship.match.repository.model.MatchAggregateBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MatchRepository {
    private final MatchDAO matchDAO;

    public MatchRepository(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }

    public List<Match> findByChampionshipId(UUID championshipId) {
        return this.matchDAO.findByChampionshipId(championshipId.toString())
                .stream()
                .map(entity -> entity.to(new MatchAggregateBuilder()))
                .toList();
    }

    public Optional<Match> findById(UUID id) {
        return this.matchDAO.findById(id.toString())
                .map(entity -> entity.to(new MatchAggregateBuilder()));
    }

    public void create(Match match) {
        this.matchDAO.create(toEntity(match));
    }

    public void delete(UUID id) {
        this.matchDAO.delete(id.toString());
    }

    private MatchEntity toEntity(Match match) {
        return MatchEntity.builder()
                .id(match.getId().toString())
                .championshipId(match.getChampionshipId().toString())
                .homeTeam(match.getHomeTeam())
                .awayTeam(match.getAwayTeam())
                .scheduledAt(match.getScheduledAt())
                .build();
    }
}
