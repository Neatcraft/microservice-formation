package fr.neatcraft.championship.championship.repository.dao;

import fr.neatcraft.championship.championship.repository.dao.entity.ChampionshipEntity;

import java.util.List;
import java.util.Optional;

public interface ChampionshipDAO {
    List<ChampionshipEntity> findAll();
    Optional<ChampionshipEntity> findById(String id);
    void create(ChampionshipEntity entity);
    void replace(ChampionshipEntity entity);
    void delete(String id);
}
