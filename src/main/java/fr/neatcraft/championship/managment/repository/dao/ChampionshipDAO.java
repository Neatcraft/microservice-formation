package fr.neatcraft.championship.managment.repository.dao;

import fr.neatcraft.championship.managment.repository.dao.entity.ChampionshipEntity;

import java.util.List;
import java.util.Optional;

public interface ChampionshipDAO {
    List<ChampionshipEntity> findAll();
    Optional<ChampionshipEntity> findById(String id);
    void create(ChampionshipEntity entity);
    void replace(ChampionshipEntity entity);
    void delete(String id);
}
