package fr.neatcraft.championship.managment.repository.dao;

import fr.neatcraft.championship.managment.repository.dao.entity.MatchEntity;

import java.util.List;
import java.util.Optional;

public interface MatchDAO {
    List<MatchEntity> findByChampionshipId(String championshipId);
    Optional<MatchEntity> findById(String id);
    void create(MatchEntity entity);
    void delete(String id);
}
