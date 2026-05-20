package fr.neatcraft.championship.match.repository.dao;

import fr.neatcraft.championship.match.repository.dao.entity.MatchEntity;

import java.util.List;
import java.util.Optional;

public interface MatchDAO {
    List<MatchEntity> findByChampionshipId(String championshipId);
    Optional<MatchEntity> findById(String id);
    void create(MatchEntity entity);
    void delete(String id);
}
