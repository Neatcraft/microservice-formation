package fr.neatcraft.championship.managment.service;

import fr.neatcraft.championship.managment.repository.MatchRepository;
import fr.neatcraft.championship.managment.repository.model.Match;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class MatchService {
    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> findByChampionship(UUID championshipId) {
        return this.matchRepository.findByChampionshipId(championshipId);
    }

    public Match findById(UUID id) {
        return this.matchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFoundException(id));
    }

    @Transactional
    public void delete(UUID id) {
        this.matchRepository.delete(id);
    }
}
